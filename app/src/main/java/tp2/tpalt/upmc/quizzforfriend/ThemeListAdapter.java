package tp2.tpalt.upmc.quizzforfriend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class ThemeListAdapter extends ArrayAdapter<ThemeList> {

    private static class ViewHolder {
        public TextView itemTitle;
        public TextView itemDesc;
        public LinearLayout lineOfMyTheme;
    }

    public ThemeListAdapter(Context context, List<ThemeList> theme) {
        super(context, R.layout.list_item_layout, theme);
    }




    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_layout, null);
            ThemeListAdapter.ViewHolder holder = new ThemeListAdapter.ViewHolder();
            holder.itemTitle = (TextView)convertView.findViewById(R.id.themeTitle);
            holder.itemDesc = (TextView)convertView.findViewById(R.id.themeDescription);
            holder.lineOfMyTheme = (LinearLayout) convertView.findViewById(R.id.lineOfMyTheme);
            convertView.setTag(holder);
        }

        final ThemeListAdapter.ViewHolder holder = (ThemeListAdapter.ViewHolder)convertView.getTag();
        final ThemeList item = getItem(position);
        holder.itemTitle.setText(item.getTitre());
        holder.itemDesc.setText(item.getDescription());

        holder.lineOfMyTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizzForFriend.playSelectPartySound(getContext());
                Intent intent = new Intent();
                intent.setClass(getContext(), ListItemDetailActivity.class);
                intent.putExtra("position", position);
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
