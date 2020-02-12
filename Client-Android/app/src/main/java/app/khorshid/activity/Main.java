package app.khorshid.activity;

import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.animation.ValueAnimator;

import androidx.databinding.DataBindingUtil;

import app.khorshid.R;
import app.khorshid.misc.Utility;
import app.khorshid.databinding.ActivityMainBinding;

public class Main extends Activity
{
    private ActivityMainBinding Binding;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

        getWindow().setStatusBarColor(Utility.Color(R.color.Primary1));
        getWindow().setNavigationBarColor(Utility.Color(R.color.Primary1));

        Binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Binding.ImageViewSearch.setOnClickListener(new View.OnClickListener()
        {
            private long IsRunning = 0;
            private boolean IsVisible = false;

            @Override
            public void onClick(View v)
            {
                if (IsRunning > System.currentTimeMillis())
                    return;

                IsRunning = System.currentTimeMillis() + 400;

                int Height = Utility.ToDP(56);

                if (IsVisible)
                {
                    ValueAnimator ValueAnimatorSearchHeight = ValueAnimator.ofInt(Height, 0);
                    ValueAnimatorSearchHeight.setDuration(350);
                    ValueAnimatorSearchHeight.addUpdateListener(animation ->
                    {
                        int Value = (Integer) animation.getAnimatedValue();

                        Binding.ConstraintLayoutSearch.getLayoutParams().height = Value;
                        Binding.ConstraintLayoutSearch.requestLayout();

                        if (Value == 0)
                        {
                            Binding.ImageViewSearch.setImageResource(R.drawable.activity_main_search);

                            IsVisible = false;
                        }
                    });
                    ValueAnimatorSearchHeight.start();

                    return;
                }

                ValueAnimator ValueAnimatorSearchHeight = ValueAnimator.ofInt(0, Height);
                ValueAnimatorSearchHeight.setDuration(350);
                ValueAnimatorSearchHeight.addUpdateListener(animation ->
                {
                    int Value = (Integer) animation.getAnimatedValue();

                    Binding.ConstraintLayoutSearch.getLayoutParams().height = Value;
                    Binding.ConstraintLayoutSearch.requestLayout();

                    if (Value == Height)
                    {
                        Binding.ImageViewSearch.setImageResource(R.drawable.activity_main_close);

                        IsVisible = true;
                    }
                });
                ValueAnimatorSearchHeight.start();
            }
        });

        Binding.ImageViewSearchDo.setOnClickListener(v ->
        {

        });
    }
}
