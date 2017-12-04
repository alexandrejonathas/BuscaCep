package cep.busca.unifavip.edu.br.buscacep;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cep.busca.unifavip.edu.br.buscacep.model.Endereco;
import cep.busca.unifavip.edu.br.buscacep.service.ICepService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ConnectivityManager connectivityManager;

    private EditText edtCep;
    private TextView tvLogradouro, tvComplemento, tvBairro, tvLocalidade, tvUf, tvUnidade, tvIbge, tvGia, tvMensagem;

    private ProgressBar progressBar;

    private Button btPesquisar;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCep = (EditText) findViewById(R.id.edtCep);
        tvLogradouro = (TextView) findViewById(R.id.tvLogradouro);
        tvComplemento = (TextView) findViewById(R.id.tvComplemento);
        tvBairro = (TextView) findViewById(R.id.tvBairro);
        tvLocalidade = (TextView) findViewById(R.id.tvLocalidade);
        tvUf = (TextView) findViewById(R.id.tvUf);
        tvUnidade = (TextView) findViewById(R.id.tvUnidade);
        tvIbge = (TextView) findViewById(R.id.tvIbge);
        tvGia = (TextView) findViewById(R.id.tvGia);
        tvMensagem = (TextView) findViewById(R.id.tvMensagem);

        btPesquisar = (Button) findViewById(R.id.bt_pesquisar);
        btPesquisar.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);

        connectivityManager = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private void limparDados(){
        tvLogradouro.setText("");
        tvComplemento.setText("");
        tvBairro.setText("");
        tvLocalidade.setText("");
        tvUf.setText("");
        tvUnidade.setText("");
        tvIbge.setText("");
        tvGia.setText("");
        tvMensagem.setText("");
        tvMensagem.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClick(View view) {
        limparDados();

        String cep = edtCep.getText().toString();
        cep = cep.replace(".", "");
        cep = cep.replace("-", "");

        if(cep.length() == 8)
            buscarDados(cep);
        else
            Toast.makeText(getBaseContext(), "O cep está inválido, verifique!", Toast.LENGTH_LONG).show();

    }

    private void buscarDados(String cep){
        progressBar.setVisibility(View.VISIBLE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://viacep.com.br/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ICepService service = retrofit.create(ICepService.class);

            Call<Endereco> call = service.get(cep);

            call.enqueue(new Callback<Endereco>() {
                @Override
                public void onResponse(Call<Endereco> call, Response<Endereco> response) {

                    Endereco endereco = response.body();

                    if(endereco.getCep() != null) {
                        tvLogradouro.setText(endereco.getLogradouro());
                        tvComplemento.setText(endereco.getComplemento());
                        tvBairro.setText(endereco.getBairro());
                        tvLocalidade.setText(endereco.getLocalidade());
                        tvUf.setText(endereco.getUf());
                        tvUnidade.setText(endereco.getUnidade());
                        tvIbge.setText(endereco.getIbge());
                        tvGia.setText(endereco.getGia());
                        progressBar.setVisibility(View.INVISIBLE);
                    }else{
                        progressBar.setVisibility(View.INVISIBLE);
                        tvMensagem.setText("Cep não encontrado!");
                        tvMensagem.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<Endereco> call, Throwable t) {
                    Log.d("CALL", t.getMessage());


                    progressBar.setVisibility(View.INVISIBLE);

                    tvMensagem.setText("Ocorreu um erro.");

                    tvMensagem.setVisibility(View.VISIBLE);
                }
            });
        }else{
            Toast.makeText(getBaseContext(), "O dispositivo não está conectado a internet", Toast.LENGTH_LONG).show();
        }
    }
}
