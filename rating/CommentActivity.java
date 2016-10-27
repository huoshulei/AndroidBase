package com.example.icogn.mshb.shb.main.my.order;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.icogn.mshb.R;
import com.example.icogn.mshb.base.BaseDataActivity;
import com.example.icogn.mshb.utils.logger.Logger;

/**
 *
 */
public class CommentActivity extends BaseDataActivity {

    private String      orderId;
    private CommentBean data;

    /**
     * 关联布局
     */
    @Override
    protected int getLayoutResId() {
        return R.layout.comment_activity;
    }

    /**
     * 加载布局
     */
    @Override
    protected void configView() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        data = new CommentBean();
        setData(data);
        orderId = getIntent().getStringExtra("orderId");
    }

    /**
     * 加载自定义菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * toolbar 事件监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_message:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_setting:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * @param view 提交评论
     */
    public void commentPush(View view) {
        // TODO: 2016/10/27  提交
    }

    /**
     * @param view 是否匿名
     */
    public void isAnonymity(View view) {
        data.setAnonymity(((CheckBox) view).isChecked());
    }
}
