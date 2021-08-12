$(function() {
	//solving the active menu
	switch(menu) {
		case 'Dashboard':
			$('index').addClass('active');
			break;
		case 'Charts':
			$('#charts').addClass('active');
			break;
		case 'Tables':
			$('#tables').addClass('active');
			break;
		case 'Navbar':
			$('#navbar').addClass('active');
			break;
		case 'Cards':
			$('#cards').addClass('active');
			break;
		case 'Login Page':
			$('#login').addClass('active');
			break;
		case 'Registration Page':
			$('#register').addClass('active');
			break;
		case 'Forgot Password Page':
			$('#forgot-password').addClass('active');
			break;
		case 'Blank Page':
			$('#blank').addClass('active');
			break;
			
		default:
			$('#home').addClass('active');
			break;
			$('#segments').addClass('active');
			$('#a_'+menu).addClass('active');
			break;
	}
	
	
})