package storm.falcon.spring;

public class StudentServiceImpl implements StudentService {

    @Override
    public String save() {
        System.out.println("real save");
        return "save";
    }
}
