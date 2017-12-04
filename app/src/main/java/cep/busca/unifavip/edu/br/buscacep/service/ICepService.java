package cep.busca.unifavip.edu.br.buscacep.service;

import java.util.List;

import cep.busca.unifavip.edu.br.buscacep.model.Endereco;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICepService {
    @GET("ws/{cep}/json")
    Call<Endereco> get(@Path("cep") String cep);
}
