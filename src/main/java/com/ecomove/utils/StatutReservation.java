package com.ecomove.utils;

/**
 * Énumération représentant le statut d'une réservation.
 *
 * @author Darryl
 * @version 1.0
 */
public enum StatutReservation {
    /** La réservation est en attente de validation par le conducteur. */
    EN_ATTENTE,
    /** La réservation a été confirmée. */
    CONFIRMEE,
    /** La réservation a été annulée par le passager ou le conducteur. */
    ANNULEE,
    /** Le trajet a été effectué. */
    TERMINEE
}
