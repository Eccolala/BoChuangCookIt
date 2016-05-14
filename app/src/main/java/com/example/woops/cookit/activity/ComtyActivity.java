package com.example.woops.cookit.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.woops.cookit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class ComtyActivity extends AppCompatActivity implements SwipeStack.SwipeStackListener, View.OnClickListener {

    private Button mButtonLeft, mButtonRight;
    private FloatingActionButton mFab;

    private ArrayList<String> mData;
    private ArrayList<Integer> mImg;
    private SwipeStack mSwipeStack;
    private SwipeStackAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comty);

        mSwipeStack = (SwipeStack) findViewById(R.id.swipeStack);
        mButtonLeft = (Button) findViewById(R.id.buttonSwipeLeft);
        mButtonRight = (Button) findViewById(R.id.buttonSwipeRight);
        mFab = (FloatingActionButton) findViewById(R.id.fabAdd);

        mButtonLeft.setOnClickListener(this);
        mButtonRight.setOnClickListener(this);
        mFab.setOnClickListener(this);

        mData = new ArrayList<>();
        mImg = new ArrayList<>();
        mAdapter = new SwipeStackAdapter(mData, mImg,this);
        mSwipeStack.setAdapter(mAdapter);
        mSwipeStack.setListener(this);

        fillWithTestData();
    }

    private void fillWithTestData() {
        for (int x = 0; x < 4; x++) {
            mData.add("这道菜真的很好吃，强烈推荐！！！");
        }
        mImg.add(R.drawable.food_01);
        mImg.add(R.drawable.food_02);
        mImg.add(R.drawable.food_04);
        mImg.add(R.drawable.food_03);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mButtonLeft)) {
            mSwipeStack.swipeTopViewToLeft();
        } else if (v.equals(mButtonRight)) {
            mSwipeStack.swipeTopViewToRight();
        } else if (v.equals(mFab)) {
            mData.add("FAB");
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onViewSwipedToRight(int position) {
        String swipedElement = mAdapter.getItem(position);
//        Toast.makeText(this, "Swipe Right", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewSwipedToLeft(int position) {
        String swipedElement = mAdapter.getItem(position);
//        Toast.makeText(this, "Swipe Left",
//                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStackEmpty() {
//        Toast.makeText(this, "Stack is Empty", Toast.LENGTH_SHORT).show();
    }

    public class SwipeStackAdapter extends BaseAdapter {
        private Context context;
        private List<String> mData;
        private List<Integer> mImg;

        public SwipeStackAdapter(List<String> data, ArrayList<Integer> mImg, Context context) {
            this.mData = data;
            this.mImg = mImg;
            this.context = context;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.card, parent, false);
            }

            TextView textViewCard = (TextView) convertView.findViewById(R.id.content);
            textViewCard.setText(mData.get(position));
            ImageView mImageView = (ImageView) convertView.findViewById(R.id.card_img);
            Picasso.with(context)
                    .load(mImg.get(position))
                    .centerCrop()
                    .resize(600,800)
                    .into(mImageView);
            return convertView;
        }
    }
}
