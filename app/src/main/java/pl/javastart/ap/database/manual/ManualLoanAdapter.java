package pl.javastart.ap.database.manual;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pl.javastart.ap.database.manual.model.Loan;
import pl.javastart.ap.database.manual.model.User;

public class ManualLoanAdapter extends BaseAdapter {

    private Context context;
    private List<Loan> loanList;

    public ManualLoanAdapter(Context context, List<Loan> loanList) {
        this.context = context;
        this.loanList = loanList;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    @Override
    public int getCount() {
        return loanList.size();
    }

    @Override
    public Loan getItem(int position) {
        return loanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView text = (TextView) convertView.findViewById(android.R.id.text1);
        Loan loan = getItem(position);
        text.setText(loan.toString());

        return convertView;
    }
}
