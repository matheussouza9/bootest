# language: pt
	Funcionalidade: Contatos

	  Cenario: Cadastro de contato
	    Dado eu estou na tela de adicionar novo contato
	    Quando eu adiciono um novo contato com nome Matheus e telefone 988776655
	    Entao o contato é adicionado a lista
	    
	  Cenario: Remoção de contato
	    Dado eu estou na tela inicial
	    Quando removo um contato com nome Matheus e telefone 988776655
	    Entao o contato é removido da lista
		      