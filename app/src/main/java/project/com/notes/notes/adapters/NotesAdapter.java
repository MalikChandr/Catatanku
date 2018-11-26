package project.com.notes.notes.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import project.com.notes.notes.activities.NotesDetail;
import project.com.notes.notes.models.Notes;
import project.com.notes.notes.R;

public class NotesAdapter extends ArrayAdapter<Notes> {

    public NotesAdapter(Context context, ArrayList<Notes> arrNotes) {
        super(context, 0, arrNotes);
    }

    class ViewHolder{
        TextView txtTitle;
        CardView cardView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final Notes notes = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(
                            R.layout.notes_list_item,
                            parent,
                            false);
            viewHolder = new ViewHolder();
            viewHolder.txtTitle = convertView.findViewById(R.id.txtTitle);
            viewHolder.cardView = convertView.findViewById(R.id.card_view);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NotesDetail.class);
                intent.putExtra("id", notes.getNotesId());
                intent.putExtra("title", notes.getNotesTitle());
                intent.putExtra("description", notes.getNotesDescription());
                intent.putExtra("date_create", notes.getNotesDateCreate());
                intent.putExtra("date_update", notes.getNotesDateUpdate());
                view.getContext().startActivity(intent);
                ((Activity)view.getContext()).finish();
            }
        });

        viewHolder.txtTitle.setText(notes.getNotesTitle());
        return convertView;
    }
}
