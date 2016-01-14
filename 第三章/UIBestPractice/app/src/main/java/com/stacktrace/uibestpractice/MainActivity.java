package com.stacktrace.uibestpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<Msg> mList = new ArrayList<Msg>();
    
    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter mMsgAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initMsgs();
        mMsgAdapter = new MsgAdapter();
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgListView.setAdapter(mMsgAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (! "".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    mList.add(msg);
                    mMsgAdapter.notifyDataSetChanged();
                    msgListView.setSelection(mList.size());
                    inputText.setText("");
                }
            }
        });

    }
    class MsgAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Msg msg = mList.get(position);
            View view;
            ViewHolder viewHolder;
            
            if (convertView == null) {
                view = View.inflate(MainActivity.this, R.layout.msg_item, null);
                viewHolder = new ViewHolder();
                viewHolder.leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
                viewHolder.rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
                viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg);
                viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg);
                view.setTag(viewHolder);
            }
            else {
                view =convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            if (msg.getType() == Msg.TYPE_SEND) {
                viewHolder.rightLayout.setVisibility(View.VISIBLE);
                viewHolder.leftLayout.setVisibility(View.GONE);
                viewHolder.rightMsg.setText(msg.getContent());
            }
            else if (msg.getType() == Msg.TYPE_RECEIVED) {
                viewHolder.rightLayout.setVisibility(View.GONE);
                viewHolder.leftLayout.setVisibility(View.VISIBLE);
                viewHolder.leftMsg.setText(msg.getContent());
            }
            return view;
        }
        class ViewHolder {
            LinearLayout leftLayout;
            LinearLayout rightLayout;
            TextView leftMsg;
            TextView rightMsg;
        }
    }
    public void initMsgs()
    {
        Msg msg1 = new Msg("Hi, What's going on these days?", Msg.TYPE_RECEIVED);
        mList.add(msg1);
        Msg msg2 = new Msg("Just so so", Msg.TYPE_SEND);
        mList.add(msg2);
        Msg msg3 = new Msg("Shall we have a party at the weekend?", Msg.TYPE_RECEIVED);
        mList.add(msg3);
    }
}
