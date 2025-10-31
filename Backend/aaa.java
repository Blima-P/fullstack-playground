<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Farmácia Saúde - Seu Marketplace de Bem-Estar</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        /* Cores principais */
        :root {
            --color-primary: #059669; /* Verde esmeralda */
            --color-secondary: #10b981; /* Verde mais claro */
            --color-text: #1f2937; /* Cinza escuro */
            --color-bg: #f3f4f6; /* Cinza claro */
        }

        body {
            /* Gradiente de fundo suave para um visual limpo */
            background: linear-gradient(135deg, #f9fafb 0%, #f3f4f6 100%);
            font-family: 'Inter', sans-serif;
            color: var(--color-text);
        }

        .hero-bg {
            /* Gradiente vibrante para o banner principal */
            background: linear-gradient(135deg, var(--color-primary) 0%, var(--color-secondary) 100%);
        }

        .card-hover {
            transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275); /* Transição elástica e suave */
        }
        .card-hover:hover {
            transform: translateY(-8px);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
        }

        .fade-in {
            animation: fadeIn 0.8s ease-out;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .gradient-text {
            background: linear-gradient(90deg, var(--color-primary), var(--color-secondary));
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
        }

        /* Estilo do Modal com Transição */
        .modal {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.6);
            backdrop-filter: blur(5px);
            opacity: 0;
            transition: opacity 0.3s ease-in-out;
        }
        .modal.open {
            display: block;
            opacity: 1;
        }
        .modal-content {
            background-color: white;
            margin: 10% auto;
            padding: 30px;
            border-radius: 16px;
            width: 90%;
            max-width: 500px;
            box-shadow: 0 25px 50px rgba(0, 0, 0, 0.4);
            transform: scale(0.9);
            transition: transform 0.3s ease-in-out;
        }
        .modal.open .modal-content {
            transform: scale(1);
        }

        /* Botão Flutuante do Carrinho Aprimorado */
        .cart-float {
            position: fixed;
            bottom: 25px;
            right: 25px;
            background: var(--color-primary);
            border-radius: 50%;
            width: 60px;
            height: 60px;
            display: flex;
            justify-content: center;
            align-items: center;
            box-shadow: 0 10px 20px rgba(5, 150, 105, 0.5); /* Sombra da cor principal */
            cursor: pointer;
            transition: all 0.3s ease;
            z-index: 1001;
            border: 3px solid white;
        }
        .cart-float:hover {
            transform: scale(1.1) rotate(5deg);
        }
        .cart-float i {
            color: white; /* Ícone branco no botão verde */
        }
        
        .badge {
            position: absolute;
            top: -5px;
            right: -5px;
            background: #ef4444; /* Vermelho vibrante */
            color: white;
            font-size: 0.75rem;
            font-weight: bold;
            border-radius: 50%;
            padding: 3px 6px;
            min-width: 20px;
            text-align: center;
            line-height: 1;
        }
    </style>
    <script>
        // Configuração do Tailwind (opcional se já estiver configurado globalmente)
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        'green-600': 'var(--color-primary)',
                        'green-700': '#047857',
                        'green-50': '#f0fdf4',
                        'blue-50': '#eff6ff',
                        'gray-800': '#1f2937'
                    }
                }
            }
        }
    </script>
</head>
<body class="min-h-screen">
    <header class="bg-white shadow-lg sticky top-0 z-50 border-b border-gray-100">
        <div class="container mx-auto px-4 md:px-8">
            <div class="flex justify-between items-center py-5">
                <div class="flex items-center space-x-3">
                    <img src="https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/cfb9e9f5-e781-4afc-92fc-d65b2d04f2fc.png" alt="Logo da Farmácia Saúde" class="w-10 h-10 rounded-full shadow-md">
                    <h1 class="text-2xl font-extrabold gradient-text">Farmácia Saúde</h1>
                </div>
                <nav class="hidden md:flex space-x-6 lg:space-x-8">
                    <a href="#home" class="text-gray-700 hover:text-green-600 transition font-medium border-b-2 border-transparent hover:border-green-600 pb-1">Início</a>
                    <a href="#categories" class="text-gray-700 hover:text-green-600 transition font-medium border-b-2 border-transparent hover:border-green-600 pb-1">Categorias</a>
                    <a href="#destaques" class="text-gray-700 hover:text-green-600 transition font-medium border-b-2 border-transparent hover:border-green-600 pb-1">Destaques</a>
                    <a href="#promocoes" class="text-gray-700 hover:text-green-600 transition font-medium border-b-2 border-transparent hover:border-green-600 pb-1">Promoções</a>
                    <a href="#contato" class="text-gray-700 hover:text-green-600 transition font-medium border-b-2 border-transparent hover:border-green-600 pb-1">Contato</a>
                </nav>
                <div class="flex items-center space-x-5">
                    <button onclick="openModal('loginModal')" class="text-gray-600 hover:text-green-600 transition p-2 rounded-full hover:bg-green-50">
                        <i class="fas fa-user-circle fa-xl"></i>
                    </button>
                    <div id="cart-icon-header" class="relative text-gray-600 cursor-pointer p-2 rounded-full hover:bg-green-50 transition" onclick="openCartModal()">
                        <i class="fas fa-shopping-cart fa-xl"></i>
                        <span id="cart-count-header" class="badge hidden">0</span>
                    </div>
                    <button class="md:hidden text-gray-600 hover:text-green-600 p-2 rounded-full hover:bg-green-50 transition" onclick="toggleMobileMenu()">
                        <i class="fas fa-bars fa-xl" id="menu-icon"></i>
                    </button>
                </div>
            </div>
            <div id="mobile-menu" class="md:hidden hidden pb-4 border-t border-gray-100">
                <nav class="flex flex-col space-y-2 pt-4">
                    <a href="#home" class="text-gray-700 hover:text-green-600 transition py-2 pl-4 rounded-lg bg-white hover:bg-green-50" onclick="toggleMobileMenu()">Início</a>
                    <a href="#categories" class="text-gray-700 hover:text-green-600 transition py-2 pl-4 rounded-lg bg-white hover:bg-green-50" onclick="toggleMobileMenu()">Categorias</a>
                    <a href="#destaques" class="text-gray-700 hover:text-green-600 transition py-2 pl-4 rounded-lg bg-white hover:bg-green-50" onclick="toggleMobileMenu()">Destaques</a>
                    <a href="#promocoes" class="text-gray-700 hover:text-green-600 transition py-2 pl-4 rounded-lg bg-white hover:bg-green-50" onclick="toggleMobileMenu()">Promoções</a>
                    <a href="#contato" class="text-gray-700 hover:text-green-600 transition py-2 pl-4 rounded-lg bg-white hover:bg-green-50" onclick="toggleMobileMenu()">Contato</a>
                </nav>
            </div>
        </div>
    </header>

    <hr>

    <section id="home" class="hero-bg text-white py-24 md:py-32 fade-in">
        <div class="container mx-auto px-4 md:px-8 text-center">
            <h2 class="text-4xl md:text-6xl font-extrabold mb-6 leading-tight drop-shadow-lg">
                Seu <span class="text-yellow-300">Bem-Estar</span> Começa Aqui
            </h2>
            <p class="text-lg md:text-xl mb-12 opacity-95 max-w-3xl mx-auto drop-shadow-md">
                Encontre medicamentos, suplementos e produtos de beleza com **entrega rápida** e a **qualidade** que você confia.
            </p>
            <div class="flex justify-center mb-10">
                <div class="relative w-full max-w-xl">
                    <input id="hero-search" type="text" placeholder="Buscar por produto, marca ou sintoma..." 
                           class="w-full px-6 py-4 pr-16 rounded-full text-gray-900 text-lg shadow-2xl focus:ring-4 focus:ring-green-300 focus:outline-none transition" 
                           onkeyup="if(event.key === 'Enter') filterProducts()">
                    <button onclick="filterProducts()" 
                            class="absolute right-2 top-2 bottom-2 bg-green-600 text-white p-3 rounded-full hover:bg-green-700 transition transform hover:scale-105">
                        <i class="fas fa-search text-xl"></i>
                    </button>
                </div>
            </div>
            <div class="flex flex-wrap justify-center gap-6 mt-12">
                <div class="bg-white bg-opacity-25 backdrop-blur-sm rounded-xl p-5 shadow-lg text-center hover:bg-opacity-40 transition">
                    <i class="fas fa-truck fa-2x mb-3 text-yellow-300"></i>
                    <p class="font-bold text-sm md:text-base">Entrega Expressa</p>
                </div>
                <div class="bg-white bg-opacity-25 backdrop-blur-sm rounded-xl p-5 shadow-lg text-center hover:bg-opacity-40 transition">
                    <i class="fas fa-shield-alt fa-2x mb-3 text-red-300"></i>
                    <p class="font-bold text-sm md:text-base">100% Original</p>
                </div>
                <div class="bg-white bg-opacity-25 backdrop-blur-sm rounded-xl p-5 shadow-lg text-center hover:bg-opacity-40 transition">
                    <i class="fas fa-prescription-bottle-alt fa-2x mb-3 text-blue-300"></i>
                    <p class="font-bold text-sm md:text-base">Farmacêutico Online</p>
                </div>
            </div>
        </div>
    </section>

    <hr>

    <section id="categories" class="py-16 bg-white">
        <div class="container mx-auto px-4 md:px-8">
            <h3 class="text-3xl md:text-4xl font-extrabold text-center mb-12 gradient-text">Escolha o que Você Precisa</h3>
            <div class="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-6 gap-6">
                <div class="text-center p-6 bg-gradient-to-br from-blue-500 to-blue-600 text-white rounded-2xl shadow-xl card-hover cursor-pointer" onclick="filterByCategory('medicamentos')">
                    <i class="fas fa-pills fa-3x mb-4"></i>
                    <h4 class="font-bold text-lg">Medicamentos</h4>
                </div>
                <div class="text-center p-6 bg-gradient-to-br from-yellow-500 to-yellow-600 text-white rounded-2xl shadow-xl card-hover cursor-pointer" onclick="filterByCategory('suplementos')">
                    <i class="fas fa-nutritionix fa-3x mb-4"></i>
                    <h4 class="font-bold text-lg">Suplementos</h4>
                </div>
                <div class="text-center p-6 bg-gradient-to-br from-purple-500 to-purple-600 text-white rounded-2xl shadow-xl card-hover cursor-pointer" onclick="filterByCategory('higiene')">
                    <i class="fas fa-soap fa-3x mb-4"></i>
                    <h4 class="font-bold text-lg">Higiene</h4>
                </div>
                <div class="text-center p-6 bg-gradient-to-br from-pink-500 to-pink-600 text-white rounded-2xl shadow-xl card-hover cursor-pointer" onclick="filterByCategory('beleza')">
                    <i class="fas fa-spa fa-3x mb-4"></i>
                    <h4 class="font-bold text-lg">Beleza</h4>
                </div>
                <div class="text-center p-6 bg-gradient-to-br from-red-500 to-red-600 text-white rounded-2xl shadow-xl card-hover cursor-pointer" onclick="filterByCategory('equipamentos')">
                    <i class="fas fa-thermometer-half fa-3x mb-4"></i>
                    <h4 class="font-bold text-lg">Equipamentos</h4>
                </div>
                <div class="text-center p-6 bg-gradient-to-br from-green-500 to-green-600 text-white rounded-2xl shadow-xl card-hover cursor-pointer" onclick="filterByCategory('primeiros_socorros')">
                    <i class="fas fa-briefcase-medical fa-3x mb-4"></i>
                    <h4 class="font-bold text-lg">Primeiros Socorros</h4>
                </div>
            </div>
        </div>
    </section>

    <hr>

    <section id="destaques" class="py-16 bg-green-50">
        <div class="container mx-auto px-4 md:px-8">
            <h3 class="text-3xl md:text-4xl font-extrabold text-center mb-12 gradient-text">Ofertas Imperdíveis em Destaque</h3>
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
                <div class="bg-white rounded-3xl p-6 shadow-2xl card-hover relative border-t-4 border-green-600 fade-in">
                    <span class="absolute top-4 right-4 bg-red-500 text-white text-sm font-bold px-3 py-1 rounded-full shadow-md">TOP VENDAS</span>
                    <img src="https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/c96079f4-eeb6-427a-8e17-000956ff9cc1.png" alt="Suplemento Multivitamínico" class="w-full h-48 object-contain rounded-xl mb-4 transition duration-300 hover:scale-[1.03]">
                    <h4 class="font-bold text-xl mb-2 text-gray-800">Suplemento Multivitamínico</h4>
                    <p class="text-gray-600 mb-4 text-sm">A dose diária de energia e imunidade para sua rotina.</p>
                    <div class="flex justify-between items-center">
                        <span class="text-3xl font-extrabold text-green-600">R$ 49,90</span>
                        <button onclick="addToCart(7)" class="bg-green-600 text-white px-5 py-2.5 rounded-full font-semibold hover:bg-green-700 transition transform hover:scale-105 shadow-md">
                            Comprar <i class="fas fa-cart-plus ml-1"></i>
                        </button>
                    </div>
                </div>
                <div class="bg-white rounded-3xl p-6 shadow-2xl card-hover relative border-t-4 border-blue-500 fade-in delay-100">
                    <span class="absolute top-4 right-4 bg-blue-500 text-white text-sm font-bold px-3 py-1 rounded-full shadow-md">NOVIDADE</span>
                    <img src="https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/a7ec2b0b-d014-4eaa-95e3-c31148f5c1f6.png" alt="Máscara N95" class="w-full h-48 object-contain rounded-xl mb-4 transition duration-300 hover:scale-[1.03]">
                    <h4 class="font-bold text-xl mb-2 text-gray-800">Máscara N95 (Pacote 5x)</h4>
                    <p class="text-gray-600 mb-4 text-sm">A mais alta proteção e conforto para o seu dia a dia.</p>
                    <div class="flex justify-between items-center">
                        <span class="text-3xl font-extrabold text-green-600">R$ 15,90</span>
                        <button onclick="addToCart(8)" class="bg-green-600 text-white px-5 py-2.5 rounded-full font-semibold hover:bg-green-700 transition transform hover:scale-105 shadow-md">
                            Comprar <i class="fas fa-cart-plus ml-1"></i>
                        </button>
                    </div>
                </div>
                <div class="bg-white rounded-3xl p-6 shadow-2xl card-hover relative border-t-4 border-pink-500 fade-in delay-200">
                    <span class="absolute top-4 right-4 bg-pink-500 text-white text-sm font-bold px-3 py-1 rounded-full shadow-md">BELEZA</span>
                    <img src="https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/dbe0d763-bd1e-47ea-8a62-ce3a5aabd884.png" alt="Creme Hidratante" class="w-full h-48 object-contain rounded-xl mb-4 transition duration-300 hover:scale-[1.03]">
                    <h4 class="font-bold text-xl mb-2 text-gray-800">Creme Hidratante Premium</h4>
                    <p class="text-gray-600 mb-4 text-sm">Fórmula leve para hidratação profunda sem deixar a pele oleosa.</p>
                    <div class="flex justify-between items-center">
                        <span class="text-3xl font-extrabold text-green-600">R$ 29,90</span>
                        <button onclick="addToCart(9)" class="bg-green-600 text-white px-5 py-2.5 rounded-full font-semibold hover:bg-green-700 transition transform hover:scale-105 shadow-md">
                            Comprar <i class="fas fa-cart-plus ml-1"></i>
                        </button>
                    </div>
                </div>
                <div class="bg-white rounded-3xl p-6 shadow-2xl card-hover relative border-t-4 border-red-500 fade-in delay-300">
                    <span class="absolute top-4 right-4 bg-yellow-500 text-gray-900 text-sm font-bold px-3 py-1 rounded-full shadow-md">EM OFERTA</span>
                    <img src="https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/487c998a-c4f2-423f-94a3-247942af7735.png" alt="Inalador para Asma" class="w-full h-48 object-contain rounded-xl mb-4 transition duration-300 hover:scale-[1.03]">
                    <h4 class="font-bold text-xl mb-2 text-gray-800">Inalador Portátil</h4>
                    <p class="text-gray-600 mb-4 text-sm">Alívio rápido para problemas respiratórios. Compacto e eficiente.</p>
                    <div class="flex justify-between items-center">
                        <span class="text-3xl font-extrabold text-green-600">R$ 89,90</span>
                        <button onclick="addToCart(10)" class="bg-green-600 text-white px-5 py-2.5 rounded-full font-semibold hover:bg-green-700 transition transform hover:scale-105 shadow-md">
                            Comprar <i class="fas fa-cart-plus ml-1"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <hr>

    <section class="py-16 bg-white">
        <div class="container mx-auto px-4 md:px-8">
            <h3 class="text-3xl md:text-4xl font-extrabold text-center mb-12 gradient-text">Catálogo Completo de Produtos</h3>
            <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8" id="products-section">
                </div>
            <div id="no-results" class="hidden text-center mt-12 text-gray-500 text-xl font-medium p-8 border-2 border-dashed border-gray-300 rounded-xl">
                <i class="fas fa-box-open fa-3x mb-4 text-gray-400"></i><br>
                Nenhum produto encontrado na busca ou categoria selecionada.
            </div>
        </div>
    </section>

    <hr>

    <section id="promocoes" class="py-20 bg-gradient-to-r from-red-500 to-orange-500 text-white">
        <div class="container mx-auto px-4 md:px-8 text-center">
            <h3 class="text-4xl font-extrabold mb-4 drop-shadow-md">🚀 Descontos de até 50% Toda Semana!</h3>
            <p class="text-xl mb-10 opacity-95 max-w-2xl mx-auto">
                Não perca as melhores ofertas! Produtos essenciais com preços que cabem no seu bolso.
            </p>
            <a href="#promocoes" class="inline-block bg-white text-red-600 px-10 py-4 rounded-full text-lg font-bold shadow-xl hover:bg-gray-100 transition transform hover:scale-105 animate-pulse">
                Ver Todas as Promoções <i class="fas fa-tag ml-2"></i>
            </a>
        </div>
    </section>

    <hr>

    <footer id="contato" class="bg-gray-900 text-white py-16">
        <div class="container mx-auto px-4 md:px-8">
            <div class="grid grid-cols-2 md:grid-cols-4 gap-10">
                <div class="col-span-2 md:col-span-1">
                    <h4 class="text-2xl font-bold mb-4 text-green-400">Farmácia Saúde</h4>
                    <p class="text-gray-400 text-sm mb-4">
                        Cuidando de você e da sua família. Qualidade, confiança e excelência em atendimento.
                    </p>
                    <div class="flex space-x-4">
                        <a href="#" class="text-gray-400 hover:text-green-400 text-xl transition"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="text-gray-400 hover:text-green-400 text-xl transition"><i class="fab fa-instagram"></i></a>
                        <a href="#" class="text-gray-400 hover:text-green-400 text-xl transition"><i class="fab fa-whatsapp"></i></a>
                        <a href="#" class="text-gray-400 hover:text-green-400 text-xl transition"><i class="fab fa-twitter"></i></a>
                    </div>
                </div>
                <div>
                    <h5 class="text-lg font-semibold mb-4 border-b-2 border-green-600 inline-block">Navegação</h5>
                    <ul class="space-y-2">
                        <li><a href="#home" class="text-gray-400 hover:text-green-400 transition text-sm">Início</a></li>
                        <li><a href="#categories" class="text-gray-400 hover:text-green-400 transition text-sm">Categorias</a></li>
                        <li><a href="#destaques" class="text-gray-400 hover:text-green-400 transition text-sm">Destaques</a></li>
                        <li><a href="#promocoes" class="text-gray-400 hover:text-green-400 transition text-sm">Promoções</a></li>
                        <li><a href="#sobre" class="text-gray-400 hover:text-green-400 transition text-sm">Sobre Nós</a></li>
                    </ul>
                </div>
                <div>
                    <h5 class="text-lg font-semibold mb-4 border-b-2 border-green-600 inline-block">Fale Conosco</h5>
                    <ul class="space-y-3 text-gray-400 text-sm">
                        <li><i class="fas fa-phone mr-2 text-green-400"></i>(11) 9999-9999</li>
                        <li><i class="fas fa-envelope mr-2 text-green-400"></i>contato@farmaciasaude.com</li>
                        <li><i class="fas fa-map-marker-alt mr-2 text-green-400"></i>Rua da Saúde, 123 - Centro</li>
                        <li><i class="fas fa-clock mr-2 text-green-400"></i>Atendimento 24h</li>
                    </ul>
                </div>
                <div>
                    <h5 class="text-lg font-semibold mb-4 border-b-2 border-green-600 inline-block">Assine a Newsletter</h5>
                    <p class="text-gray-400 text-sm mb-4">
                        Receba ofertas exclusivas e dicas de saúde.
                    </p>
                    <div class="relative">
                        <input type="email" placeholder="Seu e-mail" class="w-full px-4 py-2 rounded-lg text-gray-900 text-sm focus:ring-2 focus:ring-green-400 focus:outline-none">
                        <button class="absolute right-0 top-0 h-full bg-green-600 text-white px-4 rounded-r-lg hover:bg-green-700 transition">
                            <i class="fas fa-paper-plane"></i>
                        </button>
                    </div>
                </div>
            </div>
            <div class="border-t border-gray-700 mt-12 pt-8 text-center">
                <p class="text-gray-500 text-sm">© 2024 Farmácia Saúde. Todos os direitos reservados. | Desenvolvido com <i class="fas fa-heart text-red-500 mx-1"></i></p>
            </div>
        </div>
    </footer>

    <div id="cart-float-btn" class="cart-float" onclick="openCartModal()">
        <i class="fas fa-shopping-cart fa-2x"></i>
        <span id="cart-count-float" class="badge hidden">0</span>
    </div>

    <div id="loginModal" class="modal">
        <div class="modal-content">
            <button class="absolute top-4 right-4 text-gray-400 hover:text-gray-800 text-2xl" onclick="closeModal('loginModal')">&times;</button>
            <h3 class="text-3xl font-bold mb-6 gradient-text text-center">Acesse sua Conta</h3>
            <form class="space-y-4">
                <input type="email" placeholder="E-mail" class="w-full p-4 border border-gray-300 rounded-xl focus:ring-2 focus:ring-green-600 focus:border-green-600 transition">
                <input type="password" placeholder="Senha" class="w-full p-4 border border-gray-300 rounded-xl focus:ring-2 focus:ring-green-600 focus:border-green-600 transition">
                <button type="submit" class="w-full bg-green-600 text-white p-4 rounded-xl font-bold text-lg hover:bg-green-700 transition transform hover:scale-[1.01]">
                    Entrar <i class="fas fa-sign-in-alt ml-2"></i>
                </button>
            </form>
            <p class="text-center mt-6 text-gray-600">Não tem conta? <a href="#" class="text-green-600 font-semibold hover:underline">Cadastre-se aqui</a></p>
        </div>
    </div>

    <div id="cartModal" class="modal">
        <div class="modal-content">
            <button class="absolute top-4 right-4 text-gray-400 hover:text-gray-800 text-2xl" onclick="closeModal('cartModal')">&times;</button>
            <h3 class="text-3xl font-bold mb-6 gradient-text text-center">Seu Carrinho de Compras</h3>
            <div id="cart-items" class="space-y-4 max-h-80 overflow-y-auto pr-2">
                <p id="empty-cart-message" class="text-center text-gray-500 italic p-4 border rounded-lg hidden">Seu carrinho está vazio. Adicione um produto!</p>
            </div>
            <div class="mt-6 pt-4 border-t border-gray-200">
                <div class="flex justify-between items-center mb-4">
                    <p class="text-xl font-bold text-gray-800">Total:</p>
                    <p class="text-3xl font-extrabold text-green-600" id="cart-total">R$ 0,00</p>
                </div>
                <button onclick="checkout()" class="w-full bg-green-600 text-white p-4 rounded-xl font-bold text-lg hover:bg-green-700 transition transform hover:scale-[1.01]">
                    Finalizar Compra Segura <i class="fas fa-lock ml-2"></i>
                </button>
            </div>
        </div>
    </div>

    <script>
        // Dados dos produtos (expandidos para simulação)
        const products = [
            { id: 1, name: "Paracetamol 500mg (10 un.)", description: "Alívio rápido para dores e febre.", price: 12.90, image: "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/1f3bef44-8078-4f4c-b688-788ca08c51b4.png", alt: "Paracetamol 500mg", category: "medicamentos" },
            { id: 2, name: "Vitamina C Efervescente", description: "Reforça a imunidade e tem sabor laranja.", price: 25.50, image: "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/f948346c-927d-4f8c-a7bc-bc0d4a98930e.png", alt: "Vitamina C", category: "suplementos" },
            { id: 3, name: "Sabonete Líquido Antibac", description: "Limpeza profunda e hidratação.", price: 8.99, image: "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/a62923f0-fcf6-4a5f-998e-359a91c475d7.png", alt: "Sabonete Antibacteriano", category: "higiene" },
            { id: 4, name: "Creme Hidratante Facial Anti-Idade", description: "Hidratação e redução de linhas finas.", price: 45.00, image: "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/2df4512b-0cf6-4c65-a2e0-fed83472afaa.png", alt: "Creme Facial", category: "beleza" },
            { id: 5, name: "Termômetro Infravermelho", description: "Medição sem contato em 1 segundo.", price: 35.90, image: "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/41f0150c-79d8-4c95-b2f0-b27e6a0384d6.png", alt: "Termômetro Digital", category: "equipamentos" },
            { id: 6, name: "Curativos Adesivos - Kit Família", description: "Essenciais para pequenos acidentes.", price: 15.00, image: "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/c795a712-2f3e-4268-b276-a795820a170c.png", alt: "Kit Primeiros Socorros", category: "primeiros_socorros" },
            { id: 7, name: "Protetor Solar FPS 70 Toque Seco", description: "Proteção contra raios UV. Não oleoso.", price: 59.90, image: "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/1740afcc-47f0-403f-908f-e35c7fa1b2b1.png", alt: "Protetor Solar", category: "beleza" },
            { id: 8, name: "Álcool em Gel 70% (500ml)", description: "Higieniza e hidrata as mãos.", price: 18.00, image: "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/df7c99e3-7c65-45b2-b163-4ce49e407178.png", alt: "Máscara N95", category: "higiene" },
            { id: 9, name: "Ômega 3 Puro (60 cápsulas)", description: "Suporte para saúde cardiovascular e cerebral.", price: 69.90, image: "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/8ffb973c-5b83-4634-9cd5-368e370ec149.png", alt: "Suplemento de Ômega 3", category: "suplementos" },
            { id: 10, name: "Aspirina C (20un.)", description: "Para alívio de dor e gripes.", price: 19.50, image: "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/a7a8d195-94e0-49e1-9a40-2aa5bc3c2a3c.png", alt: "Band-Aids Assortidos", category: "medicamentos" }
        ];

        let cart = []; // Armazena objetos { product: product_object, quantity: number }
        let currentCategory = 'all';

        // --- Funções de Renderização e Filtro ---

        function renderProductCard(product) {
            return `
                <div class="bg-white rounded-2xl p-6 shadow-xl card-hover flex flex-col justify-between fade-in" data-category="${product.category}">
                    <div>
                        <img src="${product.image}" alt="${product.alt}" class="w-full h-40 object-contain rounded-xl mb-4 transition duration-300 hover:scale-[1.03] mx-auto" 
                             onerror="this.onerror=null; this.src='https://via.placeholder.com/300x160/22c55e/ffffff?text=Produto+Farmácia'">
                        <h4 class="font-bold text-xl mb-2 text-gray-800">${product.name}</h4>
                        <p class="text-gray-600 mb-4 text-sm">${product.description}</p>
                    </div>
                    <div class="flex justify-between items-center mt-auto">
                        <span class="text-2xl font-extrabold text-green-600">R$ ${product.price.toFixed(2)}</span>
                        <button onclick="addToCart(${product.id}, event)" 
                                class="bg-blue-600 text-white px-4 py-2 rounded-full font-semibold text-sm hover:bg-blue-700 transition transform hover:scale-105 shadow-md">
                            Adicionar <i class="fas fa-plus-circle ml-1"></i>
                        </button>
                    </div>
                </div>
            `;
        }

        function renderProducts(productsToShow = products) {
            const productsSection = document.getElementById('products-section');
            const noResults = document.getElementById('no-results');
            
            if (productsToShow.length === 0) {
                productsSection.innerHTML = '';
                noResults.classList.remove('hidden');
                return;
            }

            noResults.classList.add('hidden');
            productsSection.innerHTML = productsToShow.map(renderProductCard).join('');
        }

        function filterProducts() {
            const searchTerm = document.getElementById('hero-search').value.toLowerCase();
            const filtered = products.filter(p =>
                p.name.toLowerCase().includes(searchTerm) || p.description.toLowerCase().includes(searchTerm)
            );
            renderProducts(filtered);
        }

        function filterByCategory(category) {
            // Remove a seleção de todas as categorias
            document.querySelectorAll('#categories > .container > div > div').forEach(el => {
                el.classList.remove('ring-4', 'ring-offset-2', 'ring-green-400');
            });

            // Adiciona destaque visual à categoria selecionada
            if (category !== 'all') {
                 const selectedElement = Array.from(document.querySelectorAll('#categories > .container > div > div')).find(el => el.onclick.toString().includes(`'${category}'`));
                if (selectedElement) {
                    selectedElement.classList.add('ring-4', 'ring-offset-2', 'ring-green-400');
                }
            }

            currentCategory = category;
            const filtered = category === 'all' 
                ? products 
                : products.filter(p => p.category === category);
            
            // Rola para a seção de produtos
            document.getElementById('products-section').scrollIntoView({ behavior: 'smooth' });

            renderProducts(filtered);
        }
        
        // Renderiza os produtos iniciais ao carregar
        document.addEventListener('DOMContentLoaded', () => {
            renderProducts();
            updateCartUI(); // Inicializa o carrinho
        });

        // --- Funções do Carrinho ---

        function addToCart(productId, event) {
            // Previne a propagação do clique para o card-hover
            if (event) event.stopPropagation();

            const product = products.find(p => p.id === productId);
            if (!product) return;

            const existingItem = cart.find(item => item.product.id === productId);

            if (existingItem) {
                existingItem.quantity += 1;
            } else {
                cart.push({ product: product, quantity: 1 });
            }
            
            // Feedback visual de adição
            const button = event.target.closest('button');
            if(button) {
                button.innerHTML = '<i class="fas fa-check"></i> Adicionado!';
                button.classList.remove('bg-blue-600', 'hover:bg-blue-700');
                button.classList.add('bg-green-500', 'hover:bg-green-600');
                setTimeout(() => {
                    button.innerHTML = 'Adicionar <i class="fas fa-plus-circle ml-1"></i>';
                    button.classList.remove('bg-green-500', 'hover:bg-green-600');
                    button.classList.add('bg-blue-600', 'hover:bg-blue-700');
                }, 1000);
            }

            updateCartUI();
        }

        function updateCartUI() {
            const cartItemsContainer = document.getElementById('cart-items');
            const cartTotalElement = document.getElementById('cart-total');
            const cartCountHeader = document.getElementById('cart-count-header');
            const cartCountFloat = document.getElementById('cart-count-float');
            const emptyCartMessage = document.getElementById('empty-cart-message');

            // Calcular total e contagem
            const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
            const totalValue = cart.reduce((sum, item) => sum + (item.product.price * item.quantity), 0);

            // Atualizar contadores
            cartCountHeader.textContent = totalItems;
            cartCountFloat.textContent = totalItems;
            if (totalItems > 0) {
                cartCountHeader.classList.remove('hidden');
                cartCountFloat.classList.remove('hidden');
                emptyCartMessage.classList.add('hidden');
            } else {
                cartCountHeader.classList.add('hidden');
                cartCountFloat.classList.add('hidden');
                emptyCartMessage.classList.remove('hidden');
            }

            // Atualizar o total
            cartTotalElement.textContent = `R$ ${totalValue.toFixed(2).replace('.', ',')}`;

            // Renderizar itens do carrinho
            cartItemsContainer.innerHTML = cart.map(item => `
                <div class="flex items-center justify-between p-3 bg-gray-50 rounded-lg shadow-sm border-l-4 border-green-400">
                    <div class="flex items-center space-x-3">
                        <img src="${item.product.image}" alt="${item.product.alt}" class="w-12 h-12 object-contain rounded">
                        <div>
                            <p class="font-semibold text-gray-800">${item.product.name}</p>
                            <p class="text-sm text-gray-600">R$ ${item.product.price.toFixed(2).replace('.', ',')} x ${item.quantity}</p>
                        </div>
                    </div>
                    <div class="flex items-center space-x-2">
                        <button onclick="changeQuantity(${item.product.id}, -1)" class="text-gray-500 hover:text-red-500 p-1 rounded-full bg-white border"><i class="fas fa-minus"></i></button>
                        <span class="font-bold">${item.quantity}</span>
                        <button onclick="changeQuantity(${item.product.id}, 1)" class="text-gray-500 hover:text-green-500 p-1 rounded-full bg-white border"><i class="fas fa-plus"></i></button>
                        <button onclick="removeItem(${item.product.id})" class="text-red-500 hover:text-red-700 p-1 ml-2"><i class="fas fa-trash-alt"></i></button>
                    </div>
                </div>
            `).join('');
        }

        function changeQuantity(productId, delta) {
            const item = cart.find(item => item.product.id === productId);
            if (item) {
                item.quantity += delta;
                if (item.quantity <= 0) {
                    removeItem(productId);
                } else {
                    updateCartUI();
                }
            }
        }

        function removeItem(productId) {
            cart = cart.filter(item => item.product.id !== productId);
            updateCartUI();
        }

        function checkout() {
            if (cart.length > 0) {
                alert('Compra finalizada! Total: R$ ' + document.getElementById('cart-total').textContent.replace('Total: ', ''));
                cart = [];
                updateCartUI();
                closeModal('cartModal');
            } else {
                alert('Seu carrinho está vazio.');
            }
        }

        // --- Funções de Modal e Menu ---

        function openModal(modalId) {
            const modal = document.getElementById(modalId);
            modal.classList.add('open');
        }

        function closeModal(modalId) {
            const modal = document.getElementById(modalId);
            modal.classList.remove('open');
        }

        function openCartModal() {
            updateCartUI();
            openModal('cartModal');
        }

        function toggleMobileMenu() {
            const menu = document.getElementById('mobile-menu');
            const icon = document.getElementById('menu-icon');
            menu.classList.toggle('hidden');
            if (menu.classList.contains('hidden')) {
                icon.classList.remove('fa-times');
                icon.classList.add('fa-bars');
            } else {
                icon.classList.remove('fa-bars');
                icon.classList.add('fa-times');
            }
        }
        
        // Fechar modal ao clicar fora
        window.onclick = function(event) {
            const modals = document.querySelectorAll('.modal');
            modals.forEach(modal => {
                if (event.target === modal) {
                    modal.classList.remove('open');
                }
            });
        }
    </script>
</body>
</html>
