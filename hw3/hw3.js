var rs = require('readline-sync');

var fNum1 = rs.question('1st Number: ');
var fNum2 = rs.question('2nd Number: ');
var fNum3 = rs.question('3rd Number: ');
var fNum4 = rs.question('4th Number: ');

/* factorial part */
var result1 = fNum1;
for(var i=1;i < fNum1;i++){
	result1 = i * result1;
}

/* sum of digits part */
var sum2 = 0;
while (fNum2) {
	sum2 += fNum2 % 10;
	fNum2 = Math.floor(fNum2 / 10);
}

/* reverse part */
function reverseNumber(fNum3)
{
	fNum3 = fNum3 + "";
	return fNum3.split("").reverse().join("");
}
/* palindrome part */
function isPalindrome(fNum4) {
//var result4 == false
var str = fNum4;
var revStr = "";
var i = fNum4.length;
for(var j=i; j>=0; j--) {
	revStr = revStr+fNum4.charAt(j);
}
if(fNum4 == revStr) {
	return true;
} 
return false;
}
console.log('The factorial of the first number is: ' + result1);
console.log('The sum of all the digits of the second number is: ' + sum2);
console.log('The reverse of the third number is: ' + reverseNumber(fNum3));
console.log('Is the fourth number a palindrome (true/false)? ' + isPalindrome(fNum4));
