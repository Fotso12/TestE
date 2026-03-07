package com.ecomove.services.impl;

import com.ecomove.dtos.UtilisateurDTO;
import com.ecomove.entities.Utilisateur;
import com.ecomove.mapper.UtilisateurMapper;
import com.ecomove.repositories.UtilisateurRepository;
import com.ecomove.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implémentation du service Utilisateur.
 *
 * @author Darryl
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    @Override
    public UtilisateurDTO creerUtilisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDTO);
        // On pourrait ajouter un encodage de mot de passe ici
        utilisateur.setMotDePasse("defaut123"); 
        Utilisateur sauve = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDTO(sauve);
    }

    @Override
    @Transactional(readOnly = true)
    public UtilisateurDTO obtenirUtilisateurParId(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));
        return utilisateurMapper.toDTO(utilisateur);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilisateurDTO> obtenirTousLesUtilisateurs() {
        return utilisateurRepository.findAll().stream()
                .map(utilisateurMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UtilisateurDTO mettreAJourUtilisateur(Long id, UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateurExistant = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));
        
        utilisateurExistant.setNom(utilisateurDTO.getNom());
        utilisateurExistant.setPrenom(utilisateurDTO.getPrenom());
        utilisateurExistant.setEmail(utilisateurDTO.getEmail());
        utilisateurExistant.setRole(utilisateurDTO.getRole());
        
        Utilisateur misAJour = utilisateurRepository.save(utilisateurExistant);
        return utilisateurMapper.toDTO(misAJour);
    }

    @Override
    public void supprimerUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : Utilisateur non trouvé avec l'id : " + id);
        }
        utilisateurRepository.deleteById(id);
    }
}
