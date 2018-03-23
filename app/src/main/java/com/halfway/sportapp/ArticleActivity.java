package com.halfway.sportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleActivity extends AppCompatActivity implements View.OnClickListener {

    List<CategoryItem> items;
    public static RequestInterface requestInterface;
    TextView tvTime;
    TextView tvTournament;
    TextView tvPlace;
    Button btnTeam2;
    Button btnTeam1;
    Button btnStatistics;
    Button btnPrediction;
    ExpandableRelativeLayout erlTeam1;
    ExpandableRelativeLayout erlTeam2;
    ExpandableRelativeLayout erlStatistics;
    ExpandableRelativeLayout erlPrediction;
    TextView tvTeamDesc1;
    TextView tvTeamDesc2;
    TextView tvStatistics;
    TextView tvPrediction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Intent intent = getIntent();
        String str = intent.getStringExtra("article");
        String strTitle = intent.getStringExtra("title");
        String []splitArray = strTitle.split(":");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(splitArray[0]);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_left);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        requestInterface = Controller.getApi();
        items = new ArrayList<>();

        tvTime = (TextView) findViewById(R.id.tv_time);
        tvTournament = (TextView) findViewById(R.id.tv_tournament);
        tvPlace = (TextView) findViewById(R.id.tv_place);
        btnTeam1 = (Button) findViewById(R.id.btn_team1);
        btnTeam2 = (Button) findViewById(R.id.btn_team2);
        btnStatistics = (Button) findViewById(R.id.btn_statistics);
        btnPrediction = (Button) findViewById(R.id.btn_prediction);
        erlTeam1 = (ExpandableRelativeLayout) findViewById(R.id.exapndable_layout_team1);
        erlTeam2 = (ExpandableRelativeLayout) findViewById(R.id.exapndable_layout_team2);
        erlStatistics = (ExpandableRelativeLayout) findViewById(R.id.exapndable_layout_statistics);
        erlPrediction = (ExpandableRelativeLayout) findViewById(R.id.exapndable_layout_prediction);
        tvTeamDesc1 = (TextView) findViewById(R.id.tv_team1_desc);
        tvTeamDesc2 = (TextView) findViewById(R.id.tv_team2_desc);
        tvStatistics = (TextView) findViewById(R.id.tv_statistics);
        tvPrediction = (TextView) findViewById(R.id.tv_prediction);

        requestInterface.getArticle(str).enqueue(new Callback<ArticleItem>() {
            @Override
            public void onResponse(Call<ArticleItem> call, Response<ArticleItem> response) {
                ArticleItem articleItem = response.body();
                tvTime.setText(articleItem.getTime());
                tvTournament.setText(articleItem.getTournament());
                tvPlace.setText(articleItem.getPlace());
                btnTeam1.setText(articleItem.getArticle().get(0).getHeader());
                btnTeam2.setText(articleItem.getArticle().get(1).getHeader());
                btnStatistics.setText(articleItem.getArticle().get(2).getHeader());
                tvTeamDesc1.setText(articleItem.getArticle().get(0).getText());
                tvTeamDesc2.setText(articleItem.getArticle().get(1).getText());
                tvStatistics.setText(articleItem.getArticle().get(2).getText());
                tvPrediction.setText(articleItem.getPrediction());
            }

            @Override
            public void onFailure(Call<ArticleItem> call, Throwable t) {
                Toast.makeText(ArticleActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
        btnTeam1.setOnClickListener(ArticleActivity.this);
        btnTeam2.setOnClickListener(ArticleActivity.this);
        btnPrediction.setOnClickListener(ArticleActivity.this);
        btnStatistics.setOnClickListener(ArticleActivity.this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_team1:
                btnTeam1.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.chevron_up), null);
                erlTeam1.toggle();
                if (erlTeam1.isExpanded()) {
                    btnTeam1.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.chevron_down), null);
                }
                break;
            case R.id.btn_team2:
                btnTeam2.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.chevron_up), null);
                erlTeam2.toggle();
                if (erlTeam2.isExpanded()) {
                    btnTeam2.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.chevron_down), null);
                }
                break;
            case R.id.btn_statistics:
                btnStatistics.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.chevron_up), null);
                erlStatistics.toggle();
                if (erlStatistics.isExpanded()) {
                    btnStatistics.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.chevron_down), null);
                }
                break;
            case R.id.btn_prediction:
                btnPrediction.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.chevron_up), null);
                erlPrediction.toggle();
                if (erlPrediction.isExpanded()) {
                    btnPrediction.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.chevron_down), null);
                }
                break;
        }
    }
}
