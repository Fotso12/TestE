package com.ecomove.services.impl;

import com.ecomove.dtos.TrajetDTO;
import com.ecomove.entities.Trajet;
import com.ecomove.entities.Utilisateur;
import com.ecomove.mapper.TrajetMapper;
import com.ecomove.repositories.TrajetRepository;
import com.ecomove.repositories.UtilisateurRepository;
import com.ecomove.services.TrajetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implémentation du service Trajet.
 *
 * @author Darryl
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TrajetServiceImpl implements TrajetService {

    private final TrajetRepository trajetRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final TrajetMapper trajetMapper;

    @Override
    public TrajetDTO creerTrajet(TrajetDTO trajetDTO) {
        Utilisateur conducteur = utilisateurRepository.findById(trajetDTO.getConducteurId())
                .orElseThrow(() -> new RuntimeException("Conducteur non trouvé"));
        
        Trajet trajet = trajetMapper.toEntity(trajetDTO, conducteur);
        Trajet sauve = trajetRepository.save(trajet);
        return trajetMapper.toDTO(sauve);
    }

    @Override
    @Transactional(readOnly = true)
    public TrajetDTO obtenirTrajetParId(Long id) {
        Trajet trajet = trajetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trajet non trouvé"));
        return trajetMapper.toDTO(trajet);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrajetDTO> obtenirTousLesTrajets() {
        return trajetRepository.findAll().stream()
                .map(trajetMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TrajetDTO mettreAJourTrajet(Long id, TrajetDTO trajetDTO) {
        Trajet trajetExistant = trajetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trajet non trouvé"));
        
        trajetExistant.setPointDepart(trajetDTO.getPointDepart());
        trajetExistant.setPointArrivee(trajetDTO.getPointArrivee());
        trajetExistant.setDateHeureDepart(trajetDTO.getDateHeureDepart());
        trajetExistant.setPlacesDisponibles(trajetDTO.getPlacesDisponibles());
        trajetExistant.setPrix(trajetDTO.getPrix());
        trajetExistant.setConsommationCO2(trajetDTO.getConsommationCO2());
        
        Trajet misAJour = trajetRepository.save(trajetExistant);
        return trajetMapper.toDTO(misAJour);
    }

    @Override
    public void supprimerTrajet(Long id) {
        if (!trajetRepository.existsById(id)) {
            throw new RuntimeException("Trajet non trouvé");
        }
        trajetRepository.deleteById(id);
    }
}
