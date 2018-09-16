package com.example.home.myapplication;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.view.View.GONE;

public class ArticlesViewAdapter extends ArrayAdapter<ArticlesItem> {
    private Context mContext;
    private int layoutResourceId;

    private ArrayList<ArticlesItem> mListData = new ArrayList<ArticlesItem>();

    public ArticlesViewAdapter(Context mContext, int layoutResourceId, ArrayList<ArticlesItem> mListData) {
        super(mContext, layoutResourceId, mListData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mListData = mListData;
    }


    public void setListData(ArrayList<ArticlesItem> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        ArticlesViewAdapter.ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ArticlesViewAdapter.ViewHolder();
            holder.titleTextView = (TextView) row.findViewById(R.id.title);
            holder.imageView = (ImageView) row.findViewById(R.id.image);
            holder.descriptionTextView = (TextView) row.findViewById(R.id.description);
            holder.authorTextView = (TextView)row.findViewById(R.id.author);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }



        ArticlesItem item = mListData.get(position);

        if (!TextUtils.isEmpty(Html.fromHtml(item.getTitle()))) {

            holder.titleTextView.setText(Html.fromHtml(item.getTitle()));

        } else {

            holder.titleTextView.setVisibility(GONE);
        }
        if (!TextUtils.isEmpty(Html.fromHtml(item.getAuthor()))) {

            holder.authorTextView.setText(Html.fromHtml(item.getAuthor()));

        } else {

            holder.authorTextView.setVisibility(GONE);
        }

        if ((item.getDescription()).toString() != "null") {

            holder.descriptionTextView.setText(Html.fromHtml(item.getDescription()));

        } else {
            holder.descriptionTextView.setVisibility(GONE);

        }

        if ((item.getImage()).toString() != "null" && !TextUtils.isEmpty(item.getImage())) {

            Picasso.with(mContext).load(item.getImage()).into(holder.imageView);


        } else {

            holder.imageView.setImageResource(R.drawable.bb);
        }



        return row;

    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        TextView descriptionTextView;
        TextView authorTextView;

    }
}

