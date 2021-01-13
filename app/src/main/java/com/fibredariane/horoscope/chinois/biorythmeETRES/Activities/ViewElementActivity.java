package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Element;
import com.fibredariane.horoscope.chinois.biorythmeETRES.R;

public class ViewElementActivity extends AppCompatActivity {
    private TextView mTextViewElementMotCle;
    private TextView mTextViewElementDesc;
    private TextView mTextViewNomElement;
    private ImageView mImageViewElement;
    private ImageView mImageViewElementSaison;
    private ImageView mImageViewElementCouleur;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String nomElement = intent.getStringExtra("ELEMENT");

        if (nomElement != ""){
            setContentView(R.layout.activity_view_element);
            initElementLayout(nomElement);
        }
    }

    private void initElementLayout(String nomElement) {
        Element element = new Element(nomElement);
        mTextViewElementMotCle = (TextView) findViewById(R.id.text_view_element_mot_cle);
        mTextViewElementDesc = (TextView) findViewById(R.id.text_view_element_desc);
        mTextViewNomElement = (TextView) findViewById(R.id.text_view_nom_element);

        mImageViewElement = (ImageView) findViewById(R.id.image_view_element);
        mImageViewElementSaison = (ImageView) findViewById(R.id.image_view_element_saison);
        mImageViewElementCouleur = (ImageView) findViewById(R.id.image_view_element_couleur);

        setElement(element);
    }
    private void setElement(Element element){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageViewElement.setImageDrawable(getResources().getDrawable(element.getIntId(), getApplicationContext().getTheme()));
            mImageViewElementCouleur.setImageDrawable(getResources().getDrawable(element.getIntIdCouleur(), getApplicationContext().getTheme()));
            mImageViewElementSaison.setImageDrawable(getResources().getDrawable(element.getIntIdSaison(), getApplicationContext().getTheme()));

        } else {
            mImageViewElement.setImageDrawable(getResources().getDrawable(element.getIntId()));
            mImageViewElementCouleur.setImageDrawable(getResources().getDrawable(element.getIntIdCouleur()));
            mImageViewElementSaison.setImageDrawable(getResources().getDrawable(element.getIntIdSaison()));
        }
        mTextViewElementMotCle.setText(element.getDesc());
        mTextViewElementDesc.setText(element.getTextNeutre());
        mTextViewNomElement.setText(element.getNom());
        mTextViewNomElement.setTextColor(getResources().getColor(element.getIdColor()));
        mTextViewElementMotCle.setTextColor(getResources().getColor(element.getIdColor()));
    }
    public void onClickRetour(View view){
        finish();
    }
}
