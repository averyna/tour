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

    var url = "http://localhost:8080/tour/phoneCallQuery";

    if(form.firstname.value == ""){
        form.elements[0].focus();
    }
    else if(form.phone.value == ""){
        form.elements[1].focus();
    }
    else {
        var data = {};
        data.name = form.firstname.value;
        data.phone = form.phone.value;
        var json = JSON.stringify(data);

        var req = new XMLHttpRequest();
        req.open("POST", url, true);
        req.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        req.setRequestHeader("Json", json);
        req.send(json);
        document.getElementById('panel').style.display = 'none';
        document.getElementById("right").style.overflow = "hidden";
    }
    return false;
}

function mailSubmitButtonClick(form){
    //"today":new Date()
    var url = "http://localhost:8080/tour/mailSubscription";

    if(form.firstname.value == ""){
        form.elements[0].focus();
    }
    else if(form.mail.value == ""){
        form.elements[1].focus();
    }
    else {
        var data = {};
        data.name = form.firstname.value;
        data.email = form.mail.value;
        var json = JSON.stringify(data);

        var req = new XMLHttpRequest();
        req.open("POST", url, true);
        req.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        req.setRequestHeader("Json", json);
        req.send(json);
        document.getElementById('mailingPanel').style.display = 'none';
        document.getElementById("subscription").style.overflow = "hidden";
    }
    return false;
}

function confirm(form) {
    var url = "http://localhost:8080/tour/comment";
    var req = new XMLHttpRequest();
    req.open("DELETE", url, true);
    var id = form.comment_id.value;
    req.setRequestHeader("id", id);

    req.onload = function () {
        if (req.readyState == 4 && req.status == "200") {

            var children = document.getElementById("comments_list").children;
            for(i in children){

                var childrenOfComment_box = children[i].children;

                for (x in childrenOfComment_box) {

                    if(childrenOfComment_box[x].className == "author_date"){
                        var ADchildren = childrenOfComment_box[x].children;

                        for(ch in ADchildren){
                            if(ADchildren[ch].className == "del_comment"){
                                var inputs = ADchildren[ch].children;

                                for(inp in inputs){
                                    if((inputs[inp].className == "comment_tag_input") &&
                                        (inputs[inp].getAttribute("value") == id)){
                                            document.getElementById("comments_list").removeChild(children[i]);
                                        }
                                }
                            }
                        }
                    }
                }
            }

        }else{
            console.error(req.statusText);
        }
    };

    req.send(null);
    return false;
}



