function validation1()
{
	var c = check();
	if (c)
	{
        setTimeout(nextPage, 3000); //wait 3 seconds and this will automatically go to the next form
    }
    return false;
}

function nextPage()
{
	window.open("validation2.html", "_self");
}

function check()
{
	var pass = true;
	var firstName = document.getElementById("firstName").value;
	if (alphanumeric(firstName))
	{
		showCorrect("firstNameimg");
		pass = pass && true;
	}
	else
	{
		showWrong("firstNameimg");
		pass = pass && false;
	}
	var lastName = document.getElementById("lastName").value;
	if (alphanumeric(lastName))
	{
		showCorrect("lastNameimg");
		pass = pass && true;
	}
	else
	{
		showWrong("lastNameimg");
		pass = pass && false;
	}
	var gender = document.getElementById("gender").value;
	if (notEmpty(gender))
	{
		showCorrect("genderimg");
		pass = pass && true;
	}
	else
	{
		showWrong("genderimg");
		pass = pass && false;
	}
	var state = document.getElementById("state").value;
	if (notEmpty(state))
	{
		showCorrect("stateimg");
		pass = pass && true;
	}
	else
	{
		showWrong("stateimg");
		pass = pass && false;
	}
	
	return pass;
}

function alphanumeric(str)
{
	return /^[A-z0-9]+$/.test(str);
}

function notEmpty(str)
{
	return !str=="";
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