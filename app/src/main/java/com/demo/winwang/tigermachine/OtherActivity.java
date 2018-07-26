package com.demo.winwang.tigermachine;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.winwang.tigermachine.widget.OnWheelScrollListener;
import com.demo.winwang.tigermachine.widget.WheelView;
import com.demo.winwang.tigermachine.widget.adapters.ArrayWheelAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class OtherActivity extends AppCompatActivity {
    //数据源
    private String[] data = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private final static int MAX_COUNT = 11;
    //多个wheelview的id合集方便处理
    private Integer[] resIds;
    //用于标记上一次旋转的角度，用于多次抽奖的角度矫正
    private HashMap<Integer,Integer> map = new HashMap<>();

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othter);
        initAllWheel();
        initResIds();
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScroll("G25ST005865");
            }
        });
//        startScroll("G25ST005865");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startScroll("G25ST005865");
            }
        },100);
//        handler.sendEmptyMessage(1);
    }

    private void initResIds(){
        resIds = new Integer[MAX_COUNT];
        resIds[0] = R.id.wheel_0;
        resIds[1] = R.id.wheel_1;
        resIds[2] = R.id.wheel_2;
        resIds[3] = R.id.wheel_3;
        resIds[4] = R.id.wheel_4;
        resIds[5] = R.id.wheel_5;
        resIds[6] = R.id.wheel_6;
        resIds[7] = R.id.wheel_7;
        resIds[8] = R.id.wheel_8;
        resIds[9] = R.id.wheel_9;
        resIds[10] = R.id.wheel_10;
        for (int i = 0;i< MAX_COUNT;i++){
            map.put(i,0);
        }
    }

    private void initAllWheel(){
        initWheel(R.id.wheel_0);
        initWheel(R.id.wheel_1);
        initWheel(R.id.wheel_2);
        initWheel(R.id.wheel_3);
        initWheel(R.id.wheel_4);
        initWheel(R.id.wheel_5);
        initWheel(R.id.wheel_6);
        initWheel(R.id.wheel_7);
        initWheel(R.id.wheel_8);
        initWheel(R.id.wheel_9);
        initWheel(R.id.wheel_10);
    }

    /**
     * 初始化轮子
     *
     * @param id the wheel widget Id
     */
    private void initWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.setViewAdapter(new SlotMachineAdapter(this, data));
        wheel.setVisibleItems(1);
        wheel.setCyclic(true);
        wheel.setEnabled(false);
        wheel.setDrawShadows(false);
    }

    public void startScroll(String item) {
        if(item.length() != resIds.length || item.length() != MAX_COUNT){
            return;
        }
        for (int i = 0 ;i< item.length();i++){
            char c = item.charAt(i);
            int num = Arrays.binarySearch(data,String.valueOf(c));
            mixWheel(resIds[i],360 + num -map.get(i),1000+ i*500 );
            map.put(i,num);
        }
    }

    /**
     * 根据id获取轮子
     *
     * @param id the wheel Id
     * @return the wheel with passed Id
     */
    private WheelView getWheel(int id) {
        WheelView wheelView = findViewById(id);
        return wheelView;
    }

    /**
     * 转动轮子
     *
     * @param id the wheel id
     */
    private void mixWheel(int id, int round, int time) {
        WheelView wheel = getWheel(id);
        wheel.scroll(round, time);
    }

    //车轮滚动的监听器
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollingStarted(WheelView wheel) {

        }

        public void onScrollingFinished(WheelView wheel) {

        }
    };

    /**
     * 老虎机适配器
     */
    private class SlotMachineAdapter extends ArrayWheelAdapter<String> {
        private String[] items;

        public SlotMachineAdapter(Context context, String[] items) {
            super(context, items);
            this.items = items;
        }

        @Override
        public int getItemsCount() {
            return 36;
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view;
            if (cachedView != null) {
                view = cachedView;
            } else {
                view = View.inflate(OtherActivity.this, R.layout.item_other, null);
            }
            TextView textView = view.findViewById(R.id.text);
            textView.setText(items[index]);
            return view;
        }

    }
}
