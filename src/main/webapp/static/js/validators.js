function email_validator(email) {

    const regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    return ((regex.exec(email)) !== null);

}

function login_validator(login) {

    const regex = /^([a-zA-Z]+[a-zA-Z0-9]*)$/;

    return ((regex.exec(login)) !== null);

}