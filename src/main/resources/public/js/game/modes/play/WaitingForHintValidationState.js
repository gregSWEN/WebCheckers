define(function(require){
    'use strict';

    // imports
    const PlayModeConstants = require('./PlayModeConstants');
    const AjaxUtils = require('../../util/AjaxUtils');

    function WaitingForHintValidationState(controller) {
        this._controller = controller;
    }

    WaitingForHintValidationState.prototype.getName = function getName() {
        return PlayModeConstants.WAITING_FOR_HINT_VALIDATION;
    };

    WaitingForHintValidationState.prototype.onEntry = function onEntry() {
        this._controller.makeHint();

        // 1) disable UI controls
        this._controller.disableButton(PlayModeConstants.BACKUP_BUTTON_ID);
        this._controller.disableButton(PlayModeConstants.SUBMIT_BUTTON_ID);
        this._controller.disableButton(PlayModeConstants.RESIGN_BUTTON_ID);

        // 2) disable all Pieces
        this._controller.disableAllMyPieces();

        // 3) ask server to get the hint
        AjaxUtils.callServer('/makeHint', handleResponse, this);
    };

    function handleResponse(message) {
        this._controller.displayMessage(message);
        this._controller.setState(PlayModeConstants.EMPTY_TURN);
    }

    return WaitingForHintValidationState;
});