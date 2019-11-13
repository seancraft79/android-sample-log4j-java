package com.example.test_log4j;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.mindpipe.android.logging.log4j.LogConfigurator;

import org.apache.log4j.Logger;

public class MainActivity extends AppCompatActivity {

    Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = dateFormat.format(new Date());

        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Logs/";
        String fileName = "log4jFile_" + time + ".log";
        String path = dir + fileName;

        LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(path);

        // set max. number of backed up log files
        int maxBackupSize = 7;
        logConfigurator.setMaxBackupSize(maxBackupSize);

        // set max. size of log file
        long maxFileSize = 1024 * 1024 * 20;
        logConfigurator.setMaxFileSize(maxFileSize);

        logConfigurator.configure();

        Log.d("MainActivity", "LOGPATH : " + path);

        logger = Logger.getLogger("MainActivity");

        Button btnLog = findViewById(R.id.btn_log);
        btnLog.setOnClickListener(view -> onLogPressed());
    }

    public void onLogPressed() {

        Log.d("MainActivity", "onLogPressed");

        for (int i = 0; i < 10; i++) {
            logger.info("INFO_" + i);
        }

        for (int i = 0; i < 10; i++) {
            logger.error("ERROR_" + i);
        }

        for (int i = 0; i < 10; i++) {
            logger.debug("DEBUG_" + i);
        }
    }
}
