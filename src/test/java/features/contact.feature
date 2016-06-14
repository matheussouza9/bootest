# language: pt
	Funcionalidade: Contatos

	  Cenario: Cadastro de contato
	    Dado eu estou na tela de adicionar novo contato
	    Quando eu adiciono um novo contato com nome Matheus e telefone 988776655
	    Entao o contato é adicionado a lista

	  Cenario: Cadastro de contato no firefox
	    Dado Abri o firefox e estou na tela de adicionar contato
	    Quando eu adicionar nome Matheus e telefone 988776611
	    Entao o usuario devera ser redirecionado para a tela principal      