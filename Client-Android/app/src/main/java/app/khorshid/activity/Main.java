package app.khorshid.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.animation.ValueAnimator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

import app.khorshid.R;
import app.khorshid.misc.Utility;
import app.khorshid.model.Product;
import app.khorshid.databinding.ActivityMainBinding;

public class Main extends Activity
{
    private ActivityMainBinding Binding;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

        getWindow().setStatusBarColor(Utility.Color(R.color.Primary1));
        getWindow().setNavigationBarColor(Utility.Color(R.color.Primary1));

        Binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Utility.SetEditTextColor(Binding.EditTextSearch, R.color.Primary4);

        Binding.ImageViewSpeechSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });


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
            // TODO Search
        });

        AdapterProduct AdapterProductMain = new AdapterProduct();

        Binding.RecyclerViewMain.setAdapter(AdapterProductMain);
        Binding.RecyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fa-IR");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "به دنبال چه میگردید؟؟");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Not Available",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Binding.TextViewSpeechSearch.setText(result.get(0));
                }
                break;
            }

        }
    }

    private class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolderProduct>
    {
        private List<Product> ProductList = new ArrayList<>();

        private AdapterProduct()
        {
            ProductList.add(new Product(1, "چیپس مزمز 65 گرمی", "http://www.shahrvand.ir/upload/thumb3/product/6260002910316(1).jpg", 10, 45000, 1));
            ProductList.add(new Product(1, "چیپس مزمز نمکی", "http://www.shahrvand.ir/upload/thumb3/product/1473052691.jpg", 10, 655000, 1));
            ProductList.add(new Product(1, "چیپس مزمز ساده", "http://www.shahrvand.ir/upload/product/1473227814.jpg", 10, 115000, 1));
            ProductList.add(new Product(1, "چیپس مزمز 65 گرمی", "http://www.shahrvand.ir/upload/thumb3/product/6260002910316(1).jpg", 10, 45000, 1));
            ProductList.add(new Product(1, "چیپس مزمز نمکی", "http://www.shahrvand.ir/upload/thumb3/product/1473052691.jpg", 10, 655000, 1));
            ProductList.add(new Product(1, "چیپس مزمز ساده", "http://www.shahrvand.ir/upload/product/1473227814.jpg", 10, 115000, 1));
            ProductList.add(new Product(1, "چیپس مزمز 65 گرمی", "http://www.shahrvand.ir/upload/thumb3/product/6260002910316(1).jpg", 10, 45000, 1));
            ProductList.add(new Product(1, "چیپس مزمز نمکی", "http://www.shahrvand.ir/upload/thumb3/product/1473052691.jpg", 10, 655000, 1));
            ProductList.add(new Product(1, "چیپس مزمز ساده", "http://www.shahrvand.ir/upload/product/1473227814.jpg", 10, 115000, 1));
            ProductList.add(new Product(1, "چیپس مزمز 65 گرمی", "http://www.shahrvand.ir/upload/thumb3/product/6260002910316(1).jpg", 10, 45000, 1));
            ProductList.add(new Product(1, "چیپس مزمز نمکی", "http://www.shahrvand.ir/upload/thumb3/product/1473052691.jpg", 10, 655000, 1));
            ProductList.add(new Product(1, "چیپس مزمز ساده", "http://www.shahrvand.ir/upload/product/1473227814.jpg", 10, 115000, 1));
            ProductList.add(new Product(1, "چیپس مزمز 65 گرمی", "http://www.shahrvand.ir/upload/thumb3/product/6260002910316(1).jpg", 10, 45000, 1));
            ProductList.add(new Product(1, "چیپس مزمز نمکی", "http://www.shahrvand.ir/upload/thumb3/product/1473052691.jpg", 10, 655000, 1));
            ProductList.add(new Product(1, "چیپس مزمز ساده", "http://www.shahrvand.ir/upload/product/1473227814.jpg", 10, 115000, 1));

        }

        private class ViewHolderProduct extends RecyclerView.ViewHolder
        {
            private TextView TextViewAdd;
            private TextView TextViewName;
            private TextView TextViewPrice;
            private ImageView ImageViewImage;

            private ViewHolderProduct(View view)
            {
                super(view);

                TextViewAdd = view.findViewById(R.id.TextViewAdd);
                TextViewName = view.findViewById(R.id.TextViewName);
                TextViewPrice = view.findViewById(R.id.TextViewPrice);
                ImageViewImage = view.findViewById(R.id.ImageViewImage);
            }
        }

        @NonNull
        @Override
        public AdapterProduct.ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View ViewProduct = LayoutInflater.from(Main.this).inflate(R.layout.activity_main_recyclerview_main, parent, false);

            return new ViewHolderProduct(ViewProduct);
        }

        @Override
        public void onBindViewHolder(@NonNull AdapterProduct.ViewHolderProduct ViewHolderMain, int Position)
        {
            Product product = ProductList.get(Position);

            ViewHolderMain.TextViewAdd.setOnClickListener(v ->
            {

            });

            ViewHolderMain.TextViewName.setText(product.Name);

            ViewHolderMain.TextViewPrice.setText(getString(R.string.ActivityMainRecyclerViewMainPrice, String.format(Locale.ENGLISH, "%,d", product.Price)));

            Picasso.get().load(product.Image).placeholder(R.drawable.activity_main_placeholder).into(ViewHolderMain.ImageViewImage);
        }

        @Override
        public int getItemCount()
        {
            return ProductList.size();
        }
    }
}
