package com.dongdian.shenquan.ui.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.TeamListBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class MyTeamHodler extends BaseViewHolder<TeamListBean.DataBean> {
    private TextView nickName,renshu,time;
    private ImageView imageView;
    public MyTeamHodler(ViewGroup parent) {
        super(parent, R.layout.my_team_item);
        imageView=(ImageView)$(R.id.my_team_item_icon);
        nickName=(TextView)$(R.id.my_team_item_nickname);
        renshu=(TextView)$(R.id.my_team_item_renshu);
        time=(TextView)$(R.id.my_team_item_time);
    }

    @Override
    public void setData(TeamListBean.DataBean data) {
        super.setData(data);
        ShowImageUtils.showImageViewToCircle(getContext(),100,data.getAvatar(),imageView);
        time.setText(data.getRegister_time());
        nickName.setText(data.getName());
        renshu.setText(data.getTeam_num()+"");
    }
}
