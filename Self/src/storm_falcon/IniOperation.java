package storm_falcon;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.Optional;
import java.util.Vector;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class IniOperation {

    public IniOperation() {
        sectionList = new Vector<>();
    }

    private class Section {
        String comment;
        String key;
        List<Entry> entryList;
    }

    private class Entry {
        String comment;
        String key;
        String value;
    }

    private String path;
    private List<Section> sectionList;
    private Section section;
    private String strMean;
    private boolean bChanged;

    private String sep = System.getProperty("line.separator");

    public synchronized void saveIni() {
        if (!bChanged) {
            return;
        }

        try {
            if (sectionList == null) {
                return;
            }
            File file = new File(path + ".tmp");
            FileWriter fw = new FileWriter(file, false);

            Section[] objSection = new Section[sectionList.size()];
            sectionList.toArray(objSection);
            for (Section sesRoot : objSection) {
                // save section
                if (sesRoot.comment != null) {
                    fw.write(sesRoot.comment);
                    fw.write(sep);
                }
                if (sesRoot.key != null) {
                    fw.write("[");
                    fw.write(sesRoot.key);
                    fw.write("]");
                    fw.write(sep);
                }

                // save entry
                Entry[] objNote = new Entry[sesRoot.entryList.size()];
                sesRoot.entryList.toArray(objNote);
                for (Entry noteValue : objNote) {
                    if (noteValue.comment != null) {
                        fw.write(noteValue.comment);
                        fw.write(sep);
                    }
                    if (noteValue.key != null) {
                        fw.write(noteValue.key);
                        fw.write("=");
                    }
                    if (noteValue.value != null) {
                        fw.write(noteValue.value);
                    }
                    fw.write(sep);
                }
            }
            fw.close();

            Files.move(Paths.get(path + ".tmp"),
                    Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public synchronized boolean cleanIni() {
        if (sectionList == null) {
            return false;
        }
        if (sectionList.isEmpty()) {
            return true;
        }
        sectionList.clear();
        bChanged = true;
        return true;
    }

    private Optional<Section> getSection(String section) {
        return sectionList.stream()
                .filter(sec -> sec.key.equals(section))
                .findFirst();
    }

    private Optional<Entry> getEntry(Section section, String key) {
        return section.entryList.stream()
                .filter(entry -> entry.key.equals(key))
                .findFirst();
    }

    public void setKeyValue(String section, String key,
                               String newValue) {
        if (sectionList == null || section == null || key == null || newValue == null) {
            return;
        }

        Optional<Section> sectionOptional = getSection(section);
        if (!sectionOptional.isPresent()) {
            return;
        }

        Section sectionStored = sectionOptional.get();
        Optional<Entry> entryOptional = getEntry(sectionStored, key);
        if (!entryOptional.isPresent()) {
            return;
        }

        Entry entry = entryOptional.get();
        entry.value = newValue;
        bChanged = true;
    }

    public void setKeyValue(String section, String key,
                               long lNewValue) {
        setKeyValue(section, key, Long.toString(lNewValue));
    }

    public void setKeyValue(String section, String key,
                               boolean newValue) {
        setKeyValue(section, key, Boolean.toString(newValue));
    }

    public void setKeyValue(String section, String key,
                               char newValue) {
        setKeyValue(section, key, Character.toString(newValue));
    }

    public String getKeyValue(String section, String key) {
        if (sectionList == null || section == null || key == null) {
            return null;
        }

        return getSection(section)
                .map(sectionStored -> getEntry(sectionStored, key)
                        .orElse(null))
                .map(entry -> entry.value)
                .orElse(null);

    }

    public boolean getKeyValueBool(String section, String key) {
        if (sectionList == null || section == null || key == null) {
            return false;
        }
        String value = getKeyValue(section, key);
        return Boolean.valueOf(value);
    }

    public String getEntryComment(String section, String key) {
        Optional<Section> sectionOptional = getSection(section);
        if (!sectionOptional.isPresent()) {
            return null;
        }

        Section sectionStored = sectionOptional.get();
        Optional<Entry> entryOptional = getEntry(sectionStored, key);
        if (!entryOptional.isPresent()) {
            return null;
        }
        return entryOptional.get().comment;
    }

    public String getSectionComment(String section) {
        Optional<Section> sectionOptional = getSection(section);
        if (!sectionOptional.isPresent()) {
            return null;
        }

        return sectionOptional.get().comment;
    }

    public void loadIni(String path) {
        try {
            this.path = path;
            FileReader fr = new FileReader(this.path);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                loadLine(line);
                line = br.readLine();
            }
            if (section != null) {
                sectionList.add(section);
                section = null;
            }
            br.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void loadLine(String line) {
        if (line.length() == 0) {
            return;
        }

        char start = line.charAt(0);
        if (start == '#' || start == ';') {
            if (strMean != null) {
                strMean = strMean.concat(sep).concat(line);
            } else {
                strMean = line;
            }
            return;
        }

        int pos = line.indexOf("=", 0);
        if (pos != -1) {
            if (section == null) {
                return;
            }
            Entry entry = new Entry();
            entry.key = line.substring(0, pos).trim();
            entry.value = line.substring(pos + 1, line.length()).trim();
            entry.comment = strMean;
            strMean = null;
            section.entryList.add(entry);
        } else {
            if (section != null) {
                sectionList.add(section);
                section = null;
            }
            int posSt = line.indexOf("[", 0);
            if (posSt != -1) {
                int posEn = line.indexOf("]", posSt + 1);
                if (posEn > posSt) {
                    section = new Section();
                    section.key = line.substring(posSt + 1, posEn);
                    section.entryList = new Vector<>();
                    section.comment = strMean;
                    strMean = null;
                }
            }
        }
    }

}
