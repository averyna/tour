function callButtonClick() {
    document.getElementById("panel").style.display = "block";
    document.getElementById("right").style.overflow = "visible";
}
function  mailingClick () {
    document.getElementById("mailingPanel").style.display = "block";
    document.getElementById("subscription").style.overflow = "visible";
}
function callSubmitButtonClick(){
    document.getElementById('panel').style.display = 'none';
    document.getElementById("right").style.overflow = "hidden";
}
function mailSubmitButtonClick(){
    document.getElementById('mailingPanel').style.display = 'none';
    document.getElementById("subscription").style.overflow = "hidden";
}

    // window.location.href = "mailto:olga_averyna@mail.ru" + "?"
    //                     + "subject=" + "This is my subject"
    //                     + "&body=" + 'myText';

    // "window.location.href = 'mailto:olga_averyna@mail.ru?subject=This is my subject&body=myText;'"
