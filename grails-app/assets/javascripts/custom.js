function loadPage(){
    var locale = getCookie("locale");
    console.log(document.cookie);
    if(locale.toLowerCase() === "ru"){
        document.getElementById('langRU').classList.add('active');
    } else if(locale.toLowerCase() === "ua"){
        document.getElementById('langUA').classList.add('active');
    } else {
        document.getElementById('langEN').classList.add('active');
    }
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}