package com.taal.welding.assistent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taal.welding.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * BaseFragment,All Fragment will extends this
 */
public class BaseFragment extends Fragment implements View.OnClickListener {
    //@Optional
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    private MenuItem configMenuItem;
    private MainMenuClickListener mainMenuClickListener;

    protected boolean canTouched = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupToolbar();
    }


    protected void setupToolbar() {
        try{
            if (toolbar != null) {
                toolbar.setTitle("");
                ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
                toolbar.setNavigationIcon(R.mipmap.ic_menu_white);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!canTouched)
                            return;
                        if (mainMenuClickListener != null)
                            mainMenuClickListener.onMainMenuClick();
                    }
                });
            }
        }
        catch(Exception e){

        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_right, menu);
        configMenuItem = menu.findItem(R.id.menu_fragment_right);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.menu_item_view,toolbar,false);
        ImageButton btnMenu = (ImageButton) view.findViewById(R.id.ibt_menu_right);
        configMenuItem.setActionView(view);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canTouched)
                    return;
                if (mainMenuClickListener != null)
                    mainMenuClickListener.onConfigMenuClick();
            }
        });
    }


    public void onConfigMenuClick(){};

    public void setMainMenuClickListener(MainMenuClickListener mainMenuClickListener) {
        this.mainMenuClickListener = mainMenuClickListener;
    }


    @Override
    public void onClick(View v) {
    }

    public interface MainMenuClickListener {
        public void onMainMenuClick();
        public void onConfigMenuClick();
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public MenuItem getConfigMenuItem() {
        return configMenuItem;
    }

    public TextView getTvToolbarTitle() {
        return tvToolbarTitle;
    }

    public boolean onBackPressed(){
        return false;
    };
}
