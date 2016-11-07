package tp2.tpalt.upmc.quizzforfriend;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alexmaxime on 06/11/2016.
 */
public class QuizListAdapter extends ArrayAdapter<Question> {

    private static class ViewHolder {
        public TextView itemTitle;
        public TextView itemDesc;
        public ImageButton imageButton;
    }

    public QuizListAdapter(Context context, List<Question> questions) {
        super(context, R.layout.list_item_layout, questions);
    }




    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_layout, null);
            ViewHolder holder = new ViewHolder();
            holder.itemTitle = (TextView)convertView.findViewById(R.id.themeTitle);
            holder.itemDesc = (TextView)convertView.findViewById(R.id.themeDescription);
            convertView.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder)convertView.getTag();
        final Question item = getItem(position);
        holder.itemTitle.setText("Titre");
        holder.itemDesc.setText("Description");

        return convertView;
    }
}
