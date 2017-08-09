package bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class listViewAdapter extends BaseAdapter {

    List<Usuario> usuarioList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public listViewAdapter(Context context, List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return usuarioList.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.item_list_view,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Usuario usuario = (Usuario) getItem(position);
        viewHolder.tvTitle.setText(usuario.getNombre());
        Picasso.with(context).load(usuario.getTwitter()).into(viewHolder.imageView);
        return convertView;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView tvTitle;

        public ViewHolder(View itemView) {
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            tvTitle = (TextView)itemView.findViewById(R.id.title);
        }
    }
}
