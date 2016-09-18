package com.ge.adsl.session.consumer;

import com.ge.adsl.DialupInfo;
import com.ge.util.IniOperation;
import com.ge.util.log.Log;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Properties;

/**
 * Created by Storm_Falcon on 2016/9/13.
 *
 */
public class SessionDialupThread extends Thread {

	private KafkaConsumer<String, DialupInfo> consumer;

	private Log log;

	private DateTimeFormatter formatter;

	private IniOperation mIni;

	public SessionDialupThread(Properties properties, String section) {
		initIni();

		initConsumer(section, properties);

		initLog(section);

		formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss.SSS");
	}

	private void initIni() {
		mIni = new IniOperation();
		String path = System.getProperty("user.dir") + File.separatorChar + "KafkaSessionConsumer.ini";
		if (!mIni.loadIni(path)) {
			System.out.println(path + " error!");
			System.exit(-1);
		}
	}

	private void initConsumer(String section, Properties properties) {
		consumer = new KafkaConsumer<>(properties);

		String topic = mIni.getKeyValue(section, "topic");
		int partition = (int) mIni.getKeyValueInt(section, "partition");
		if (partition == -1) {
			partition = 0;
		}
		long offset = mIni.getKeyValueInt(section, "offset");

		TopicPartition tp = new TopicPartition(topic, partition);
		consumer.assign(Collections.singletonList(tp));

		if (offset != -1) {
			consumer.seek(tp, offset);
		}
	}

	private void initLog(String section) {
		String logPath = mIni.getKeyValue(section, "logPath");
		int logChangeTime = (int) mIni.getKeyValueInt(section, "logChangeTime");

		log = Log.getLog(logPath, logChangeTime);
		log.setShowSystemOut(true);
		log.setShowT(false);
	}

	public void run() {
		while (true) {
			ConsumerRecords<String, DialupInfo> records = consumer.poll(100);
			records.forEach(record -> log.toLog(
				record.key() + "," + record.partition() + "," + record.offset() + "," +
					LocalDateTime.now().format(formatter) + "," +
					record.value().toString()
			));
		}
	}
}
