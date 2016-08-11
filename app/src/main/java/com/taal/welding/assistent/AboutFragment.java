package com.taal.welding.assistent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.taal.welding.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2015-08-13.
 */
public class AboutFragment extends Fragment {
//    implements
//    RevealBackgroundView.OnStateChangeListener

    private OnMainMenuClickListener onMainMenuClickListener;

    @Bind(R.id.ibt_about_main_menu)
    ImageButton ibtMainMenu;

//    @InjectView(R.id.revealBackgroundView)
//    RevealBackgroundView revealBackgroundView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        ibtMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMainMenuClickListener != null)
                    onMainMenuClickListener.onMainMenuClick();
            }
        });

//        revealBackgroundView.setOnStateChangeListener(this);
//        revealBackgroundView.startFromLocation(new int[]{revealBackgroundView.getWidth() / 2, 0});
    }

    public void setOnMainMenuClickListener(OnMainMenuClickListener onMainMenuClickListener) {
        this.onMainMenuClickListener = onMainMenuClickListener;
    }

//    @Override
//    public void onStateChange(int state) {
//        if (state == RevealBackgroundView.STATE_FINISHED)
//           revealBackgroundView.setVisibility(View.GONE);
//    }

    public interface OnMainMenuClickListener{
        public void onMainMenuClick();
    }
}
