function validation2()
{
    var c = check();
    if (c)
    {
        setTimeout(showAlert, 1000); //submission message pops up 1 second after hitting submit
    }
    return false;
}

function showAlert()
{
    alert("Submission recieved!");
}

function check()
{
    var pass = true;
    var email = document.getElementById("email").value;
    if (mailCheck(email))
    {
        showCorrect("mailimg");
        pass = pass && true;
    }
    else
    {
        showWrong("mailimg");
        pass = pass && false;
    }
    var phone = document.getElementById("phone").value;
    if (phoneCheck(phone))
    {
        showCorrect("phoneimg");
        pass = pass && true;
    }
    else
    {
        showWrong("phoneimg");
        pass = pass && false;
    }
    var address = document.getElementById("address").value;
    if (addressCheck(address))
    {
        showCorrect("addressimg");
        pass = pass && true;
    }
    else
    {
        showWrong("addressimg");
        pass = pass && false;
    }
    
    return pass;
}

function mailCheck(str)
{
    return /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/.test(str);
}

function phoneCheck(str)
{
    var digits = /^\d{10}$/.test(str);
    var dash = /^[0-9]{3}-[0-9]{3}-[0-9]{4}$/.test(str);
    return digits || dash;
}

function addressCheck(str)
{
   return !/^[A-Za-z,]+$/.test(str);
}

function showCorrect(id)
{
    document.getElementById(id).hidden = false;
    document.getElementById(id).src = "correct.png";
}

function showWrong(id)
{
    document.getElementById(id).hidden = false;
    document.getElementById(id).src = "wrong.png";
}