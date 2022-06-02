import {ERRORS_ADDED, ERRORS_DISMISSED_FIRST} from "./errors-c";

export const errorsDismissedFirst = () => ({type: ERRORS_DISMISSED_FIRST})
export const errorsAdded = (msg) => ({type: ERRORS_ADDED, payload: msg})
