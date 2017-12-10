function callButtonClick() {
    document.getElementById("panel").style.display = "block";
    document.getElementById("right").style.overflow = "visible";
}
function  mailingClick () {
    document.getElementById("mailingPanel").style.display = "block";
    document.getElementById("subscription").style.overflow = "visible";
}

function hide(){
    document.getElementById('panel').style.display = 'none';
    document.getElementById("right").style.overflow = "hidden";
}

function callSubmitButtonClick(form){
    document.getElementById('panel').style.display = 'none';
    document.getElementById("right").style.overflow = "hidden";

    var url = "http://localhost:8080/tour/phoneCallQuery";
    var data = {};
    data.name = form.firstname.value;
    data.ph = form.phone.value;
    var json = JSON.stringify(data);

    var req = new XMLHttpRequest();
    req.open("POST", url, true);
    req.setRequestHeader('Content-type','application/json; charset=utf-8');
    req.setRequestHeader("Json", json);
    req.send(json);
    return false;
}

function confirm(form) {
    var url = "http://localhost:8080/tour/comment";
    var req = new XMLHttpRequest();
    req.open("DELETE", url, true);
    req.setRequestHeader("id", form.comment_id.value);

    //todo: добавить обновление отзывов, т.е. чтобы удаленный отзыв исчезал
//    req.onload = function () {
//        var users = JSON.parse(xhr.responseText);
//        if (xhr.readyState == 4 && xhr.status == "200") {
//            console.table(users);
//        } else {
//            console.error(users);
//        }
//    }
    req.send(null);
    return false;
}


function mailSubmitButtonClick(){
    document.getElementById('mailingPanel').style.display = 'none';
    document.getElementById("subscription").style.overflow = "hidden";
    //"today":new Date()
}


