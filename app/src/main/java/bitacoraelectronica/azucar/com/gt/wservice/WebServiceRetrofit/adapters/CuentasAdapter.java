package bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bitacoraelectronica.azucar.com.gt.wservice.R;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.models.CuentaBancaria;

/**
 * Created by Ebarrios on 07/08/2017.
 */

public class CuentasAdapter extends RecyclerView.Adapter<CuentasAdapter.ViewHolderCuentas> {

    private List<CuentaBancaria> lista;

    public CuentasAdapter(List<CuentaBancaria> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolderCuentas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cobrodoc,null,false);
        return new ViewHolderCuentas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderCuentas holder, int position) {
        holder.tvNumeroCuenta.setText(lista.get(position).getNumeroCuenta());
        holder.tvNombreCuenta.setText(lista.get(position).getNombreCuenta());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolderCuentas extends RecyclerView.ViewHolder {
        TextView tvNumeroCuenta,tvNombreCuenta;

        public ViewHolderCuentas(View itemView) {
            super(itemView);

            tvNumeroCuenta = (TextView)itemView.findViewById(R.id.tvNumeroCuenta);
            tvNombreCuenta = (TextView)itemView.findViewById(R.id.tvNombreCuenta);
        }
    }
}
