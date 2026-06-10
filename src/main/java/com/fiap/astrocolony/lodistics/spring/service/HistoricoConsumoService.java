package com.fiap.astrocolony.lodistics.spring.service;

import com.fiap.astrocolony.lodistics.spring.dto.requests.HistoricoConsulmoRequest;
import com.fiap.astrocolony.lodistics.spring.entity.Alimento;
import com.fiap.astrocolony.lodistics.spring.entity.HistoricoConsumo;
import com.fiap.astrocolony.lodistics.spring.entity.Tripulante;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.RegraNegocioException;
import com.fiap.astrocolony.lodistics.spring.repository.AlimentoRepository;
import com.fiap.astrocolony.lodistics.spring.repository.HistoricoConsumoRepository;
import com.fiap.astrocolony.lodistics.spring.repository.TripulanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoricoConsumoService {

    private final HistoricoConsumoRepository historicoConsumoRepository;
    private final AlimentoRepository alimentoRepository;
    private final TripulanteRepository tripulanteRepository;


    public HistoricoConsumo salvar(HistoricoConsulmoRequest historicoConsumo) {
        if (historicoConsumo.idAlimento() == 0 || historicoConsumo.idTripulante() == 0) {
            throw new RegraNegocioException("Verifique os IDs informados!");
        }
        Optional<Alimento> alimento = alimentoRepository.findById(historicoConsumo.idAlimento());
        if (alimento.isEmpty()){
            throw new RegraNegocioException("Alimento não encontrado!");
        }
        Optional<Tripulante> tripulante = tripulanteRepository.findById(historicoConsumo.idTripulante());
        if (tripulante.isEmpty()){
            throw new RegraNegocioException("Alimento não encontrado!");
        }
        HistoricoConsumo historicoConsumo1 = HistoricoConsumo.builder()
                .qtdConsumida(alimento.get().getPesoAlimento())
                .alimento(alimento.get())
                .tripulante(tripulante.get())
                .build();
        return historicoConsumoRepository.save(historicoConsumo1);
    }

    public Page<HistoricoConsumo> listarHistoricoConsumo(Pageable pageable){
        return historicoConsumoRepository.findAll(pageable);
    }

}
