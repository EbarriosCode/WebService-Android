package bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bitacoraelectronica.azucar.com.gt.wservice.R;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.pojo.Usuario;

/**
 * Created by Ebarrios on 08/08/2017.
 */

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder>{

    List<Usuario> usuarioList = new ArrayList<>();
    Context context;

    public recyclerViewAdapter(Context context,List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_recycler,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(usuarioList.get(position).getNombre());
        Picasso.with(context).load(usuarioList.get(position).getTwitter()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvTitle;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.imageViewRecycler);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitleRecycler);
            cardView = (CardView)itemView.findViewById(R.id.card);
        }
    }
}
