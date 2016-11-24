package com.example.katja.zdravila.Seznam_zdravil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.DataAll;
import com.example.Zdravilo;
import com.example.katja.zdravila.R;
import com.example.katja.zdravila.activity_Novo_zdravilo;

/**
 * Created by Katja on 28. 05. 2016.
 */
public class seznam_adapter extends RecyclerView.Adapter<seznam_adapter.zdravila_holder> {

    private DataAll listData;
    private LayoutInflater inflater;
    private Activity vhodni_act;
    private ItemClickCallback itemClickCallback;


    public interface ItemClickCallback {
        int onItemClick(int p);
        int onSecondaryiconClick(int p);

    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public seznam_adapter(DataAll list_data, Context c, Activity nekaj) {
        this.inflater = LayoutInflater.from(c);
        this.listData = list_data;
        this.vhodni_act = nekaj;
    }

    class zdravila_holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView poimenovanje_zdravila;
        private TextView zaloga_zdravil;
        private ImageButton povecaj_zalogo;
        private View containter;

        public zdravila_holder(View itemView) {
            super(itemView);

            poimenovanje_zdravila = (TextView) itemView.findViewById(R.id.txtImeZdravila);
            zaloga_zdravil = (TextView) itemView.findViewById(R.id.txtZaloga);
            povecaj_zalogo = (ImageButton) itemView.findViewById(R.id.imgBtnDodaj);
           // povecaj_zalogo.setOnClickListener(this);
            containter = itemView.findViewById(R.id.cont_item_root);
            containter.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
         /*   if (v.getId() == R.id.cont_item_root) {
                itemClickCallback.onItemClick(getAdapterPosition());
            } else {
                itemClickCallback.onSecondaryiconClick(getAdapterPosition());
            }*/
            Intent novo_okno = new Intent(new Intent(vhodni_act, activity_Novo_zdravilo.class));
            novo_okno.putExtra("vhod",getAdapterPosition());
            vhodni_act.startActivityForResult(novo_okno,1);
        }
    }

    @Override
    public zdravila_holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.activity_list_item, parent, false);
        return new zdravila_holder(view);
    }

    @Override
    public void onBindViewHolder(zdravila_holder holder, int position) {

            Zdravilo item = listData.getListZdravil().get(position);
            holder.poimenovanje_zdravila.setText(item.getPoimenovanje());
            holder.zaloga_zdravil.setText(item.getSt_tablet().toString());
            //holder.ikona.setImageResource(item.getImageResId());
    }

    @Override
    public int getItemCount() {
        return listData.getListZdravil().size();
    }


}
