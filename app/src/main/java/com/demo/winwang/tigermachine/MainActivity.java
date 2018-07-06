package com.demo.winwang.tigermachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.winwang.tigermachine.widget.OnWheelScrollListener;
import com.demo.winwang.tigermachine.widget.WheelView;
import com.demo.winwang.tigermachine.widget.adapters.AbstractWheelAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWheel(R.id.dialog_slot_1);
        initWheel(R.id.dialog_slot_2);
        initWheel(R.id.dialog_slot_3);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScrool(456);
            }
        });
        findViewById(R.id.btn_other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,OtherActivity.class);
                startActivity(it);
            }
        });

    }

    /**
     * 初始化轮子
     *
     * @param id the wheel widget Id
     */
    private void initWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.setViewAdapter(new SlotMachineAdapter());
        wheel.setVisibleItems(3);
        if (id == R.id.dialog_slot_3) {
            wheel.addScrollingListener(scrolledListener);
        }
        wheel.setCyclic(true);
        wheel.setEnabled(false);
        wheel.setDrawShadows(false);
    }

    //车轮滚动的监听器
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollingStarted(WheelView wheel) {

        }

        public void onScrollingFinished(WheelView wheel) {

        }
    };

    public void startScrool(Integer num) {
//        char c0 = item.charAt(0);
//        int num0 = Arrays.binarySearch(data,String.valueOf(c0));
//        mixWheel(R.id.wheel_0, 360+ num0 - map.get(0), 2000);
//        map.put(0,num0);
//
//
//        char c1 = item.charAt(1);
//        int num1 = Arrays.binarySearch(data,String.valueOf(c1));
//        mixWheel(R.id.wheel_1, 360 + num1 - map.get(1), 3000);
//        map.put(1,num1);
//
//        char c2 = item.charAt(2);
//        int num2 = Arrays.binarySearch(data,String.valueOf(c2));
//        mixWheel(R.id.wheel_2, 360 + num2- map.get(2) , 5000);
//        map.put(2,num2);
        String numString = num.toString();
        int length = numString.length();
        if (length == 1) {
            mixWheel(R.id.dialog_slot_1, 50, 2000);
            mixWheel(R.id.dialog_slot_2, 70, 3000);
            mixWheel(R.id.dialog_slot_3, 90 + num, 5000);
        } else if (length == 2) {
            mixWheel(R.id.dialog_slot_1, 50, 3000);
            char c = numString.charAt(0);
            int firstNum = Integer.parseInt(String.valueOf(c));
            mixWheel(R.id.dialog_slot_2, 70 + firstNum, 2000);
            char c1 = numString.charAt(1);
            int secondNum = Integer.parseInt(String.valueOf(c1));
            mixWheel(R.id.dialog_slot_3, 90 + secondNum, 3000);
        } else if (length == 3) {
            char c = numString.charAt(0);
            int firstNum = Integer.parseInt(String.valueOf(c));
            mixWheel(R.id.dialog_slot_1, 360+ firstNum, 3000);
            char c1 = numString.charAt(1);
            int secondNum = Integer.parseInt(String.valueOf(c1));
            mixWheel(R.id.dialog_slot_2, 40+ secondNum, 3000);
            char c2 = numString.charAt(2);
            int thirdNum = Integer.parseInt(String.valueOf(c2));
            mixWheel(R.id.dialog_slot_3, 90+ thirdNum, 3000);
        }
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


    /**
     * 根据id获取轮子
     *
     * @param id the wheel Id
     * @return the wheel with passed Id
     */
    private WheelView getWheel(int id) {
        WheelView wheelView = (WheelView) findViewById(id);
        return wheelView;
    }


    /**
     * 老虎机适配器
     */
    private class SlotMachineAdapter extends AbstractWheelAdapter {
        @Override
        public int getItemsCount() {
            return 10;
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view;
            if (cachedView != null) {
                view = cachedView;
            } else {
                view = View.inflate(MainActivity.this, R.layout.item_dialog_tiger_img, null);
            }
            ImageView img = (ImageView) view.findViewById(R.id.iv_dialog_home_tiger);
            switch (index) {
                case 0:
                    img.setImageResource(R.mipmap.png0);
                    break;

                case 1:
                    img.setImageResource(R.mipmap.png1);
                    break;

                case 2:
                    img.setImageResource(R.mipmap.png2);
                    break;

                case 3:
                    img.setImageResource(R.mipmap.png3);
                    break;

                case 4:
                    img.setImageResource(R.mipmap.png4);
                    break;

                case 5:
                    img.setImageResource(R.mipmap.png5);
                    break;

                case 6:
                    img.setImageResource(R.mipmap.png6);
                    break;

                case 7:
                    img.setImageResource(R.mipmap.png7);
                    break;

                case 8:
                    img.setImageResource(R.mipmap.png8);
                    break;

                case 9:
                    img.setImageResource(R.mipmap.png9);
                    break;

            }

            return view;
        }
    }
}
