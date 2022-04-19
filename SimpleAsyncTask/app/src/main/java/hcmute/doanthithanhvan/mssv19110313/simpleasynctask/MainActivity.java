package hcmute.doanthithanhvan.mssv19110313.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus, tvPercent;
    private Button btnStart;
    private ProgressBar pgbTask;

    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvPercent = (TextView) findViewById(R.id.tvPercent);
        btnStart = (Button) findViewById(R.id.btnStart);
        pgbTask = (ProgressBar) findViewById(R.id.pgbTask);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doStartTask();
            }
        });
    }
    private void doStartTask()  {
        final int MAX = 100;
        this.pgbTask.setMax(MAX);

        Thread thread = new Thread(new Runnable()  {

            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        btnStart.setEnabled(false);
                        tvStatus.setText("Napping...");
                    }
                });
                for( int i =0; i < MAX; i++) {
                    final int progress = i + 1;
                    SystemClock.sleep(600);

                    handler.post(new Runnable() {
                        public void run() {
                            pgbTask.setProgress(progress);
                            int percent = (progress * 100) / MAX;

                            tvPercent.setText(" " + percent + " %");
                            if(progress == MAX)  {
                                tvStatus.setText("Working...");
                                btnStart.setEnabled(true);
                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }
}