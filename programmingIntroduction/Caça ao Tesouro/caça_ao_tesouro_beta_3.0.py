# Observações
# Nescessario instalar a Biblioteca PYGAME
# Nescessario instalar a fonte PIRATA ONE
# Jogo na versão Beta, faltando alguns detalhes

### Importando as Biblioteca
import random
import pygame

### Criando as Funções
def criar_campo (linha,coluna, tesouro, buraco):
    '''(linhas do campo, Colunas do campo)
        cria a estrutura do campo minado'''
    campo = []
    for i in range(linha):
        linha = []
        for j in range(coluna):
            linha.append(int(0))
        campo.append(linha)
    colocando_tesouro(campo, tesouro)
    colocando_buraco(campo, buraco)
    scanner(campo)
    return campo

def colocando_tesouro (campo, tesouro):
    '''(Matriz campo, Nº de tesouro)
        coloca as tesouro randomicamente'''
    tesouros = 0
    while tesouros < tesouro:
        x = random.randint(0,len(campo)-1)
        y = random.randint(0,len(campo[0])-1)
        if (campo[x][y] == 0):
            campo [x][y] = "t"
            tesouros += 1
def colocando_buraco (campo, buraco):
    '''(Matriz Campo, Nº de buracos)
        coloca as buracos randomicamente'''
    buracos = 0
    while buracos < buraco:
        x = random.randint(0,len(campo)-1)
        y = random.randint(0,len(campo[0])-1)
        if (campo[x][y] == 0):
            campo [x][y] = "b"
            buracos += 1        
def scanner(campo):
    '''
    (Matriz campo a scannear)
    Scanear o campo e informa os baús proximos
    '''
    #Verifica as casas do meio da matriz
    for i in range(1, len(campo)-1):
        for j in range(1, len(campo[0])-1):
        
            if (campo[i][j] == 0):
                if (campo[i][j+1] == "t"):
                    campo[i][j] += 1
                    
                if (campo[i][j-1] == "t"):
                    campo[i][j] += 1
                    
                if (campo[i+1][j] == "t"):
                    campo[i][j] += 1
                    
                if (campo[i-1][j] == "t"):
                    campo[i][j] += 1

    #Verifica as casas da parte de direita da matriz                
    for i in range (1, len(campo)-1):
        if (campo[i][0] == 0):
                                 
            if (campo[i][1] == "t"):
                campo[i][0] += 1
                    
            if (campo[i+1][0] == "t"):
                campo[i][0] += 1
                    
            if (campo[i-1][0] == "t"):
                campo[i][0] += 1

    #Verifica as casas da parte de esquerda da matriz                
    for i in range (1, len(campo)-1):
        if (campo[i][len(campo[0])-1] == 0):
            if (campo[i][len(campo[0])-2] == "t"):
                campo[i][len(campo[0])-1] += 1
                    
            if (campo[i+1][len(campo[0])-1] == "t"):
                campo[i][len(campo[0])-1] += 1
                    
            if (campo[i-1][len(campo[0])-1] == "t"):
                campo[i][len(campo[0])-1] += 1

    #Verifica as casas da parte de cima da matriz
    for j in range (1, len(campo)-1):
        if (campo[0][j] == 0):
                                 
            if (campo[1][j] == "t"):
                campo[0][j] += 1
                    
            if (campo[0][j+1] == "t"):
                campo[0][j] += 1
                    
            if (campo[0][j-1] == "t"):
                campo[0][j] += 1
    #Verifica as casas da parte de baixo da matriz
    for j in range (1, len(campo)-1):
        if (campo[len(campo)-1][j] == 0):
            if (campo[len(campo)-2][j] == "t"):
                campo[len(campo)-1][j] += 1
                    
            if (campo[len(campo)-1][j+1] == "t"):
                campo[len(campo)-1][j] += 1
                    
            if (campo[len(campo)-1][j-1] == "t"):
                campo[len(campo)-1][j] += 1

    #Verifica as casas os cantos da matriz
    if (campo [0][0] == 0):
            if (campo[0][1] == "t"):
                campo[0][0] += 1
                    
            if (campo[1][0] == "t"):
                campo[0][0] += 1
    if (campo [0][len(campo)-1] == 0):
            if (campo[0][len(campo)-2] == "t"):
                campo[0][len(campo)-1] += 1
                    
            if (campo[1][len(campo)-1] == "t"):
                campo[0][len(campo)-1] += 1
    if (campo [len(campo)-1][0] == 0):
            if (campo[len(campo)-1][1] == "t"):
                campo[len(campo)-1][0] += 1
                    
            if (campo[len(campo)-2][0] == "t"):
                campo[len(campo)-1][0] += 1
                
    if (campo [len(campo)-1][len(campo)-1] == 0):
            if (campo[len(campo)-1][len(campo)-2] == "t"):
                campo[len(campo)-1][len(campo)-1] += 1
                    
            if (campo[len(campo)-2][len(campo)-1] == "t"):
                campo[len(campo)-1][len(campo)-1] += 1

#Criando os botões do jogo

def colocando_botoes(tela, campo, i, j, imagem):
    '''
    (Tela, Matriz, i, j, Imagem)
    '''
    campo[i][j]=tela.blit(imagem, (((i+1)*100)+30,((j+1)*100)+124))
#Verifica a casa
    
def verificando_casa (campo, i, j):
    referencia[i][j] = 1
    if (campo [i][j] == 't'):
        screen.blit(tesouro, (((j+1)*100)+30,((i+1)*100)+124))
        return 't'
    elif (campo [i][j] == 'b'):
        screen.blit(buraco, (((j+1)*100)+30,((i+1)*100)+124))
        return 'b'    
    elif (campo [i][j] == 1):
        screen.blit(numero1, (((j+1)*100)+30,((i+1)*100)+124))
    elif (campo [i][j] == 2):
        screen.blit(numero2, (((j+1)*100)+30,((i+1)*100)+124))
    elif (campo [i][j] == 3):
        screen.blit(numero3, (((j+1)*100)+30,((i+1)*100)+124))
    elif (campo [i][j] == 4):
        screen.blit(numero4, (((j+1)*100)+30,((i+1)*100)+124))
    else :
        screen.blit(numero0, (((j+1)*100)+30,((i+1)*100)+124))
    return 0
    
def pontuacao ():
    '''
    (Casa, Vez do jogador, Jogador 1, Jogador2)
    Efetua a pontuação
    '''
    if (jogador1[1] == True):
        if (casa == "t"):
            jogador1[0] += 100           
        elif (casa == "b" and jogador1[0] >=50):
            jogador1[0] -= 50
    else:
        if (casa == "t"):
            jogador2[0] += 100            
        elif (casa == "b" and jogador2[0] >=50):
            jogador2[0] -= 50    

    if (jogador1[1] == False):
        jogador1[1] = True
        jogador2[1] = False
    else:
        jogador1[1] = False
        jogador2[1] = True

def placar():

    if (jogador2[1] == True):
        screen.blit(escuro, (571,221))
    else: 
        screen.blit(claro, (571,221))
        
    texto = pirata1.render("%d" %++jogador1[0],False,white)
    screen.blit(texto,(620, 280))
    texto = pirata.render( "Jogador 1",False,white)
    screen.blit(texto,(620,225))

    if (jogador1[1] == True):
        screen.blit(escuro, (831,221))
    else: 
        screen.blit(claro, (831,221))

    texto = pirata1.render("%d" %++jogador2[0],False,white)
    screen.blit(texto,(870, 280))
    texto = pirata.render( "Jogador 2 ",False,white)
    screen.blit(texto,(870,225))

def terminou():
    soma = 0
    for i in range (len(referencia)):
        for j in range (len(referencia[0])):
            soma += referencia[i][j]
    return soma

#Criando o campo de jogo 1---------------------------------INICIO DO CORPO------------------------------------------------
campo_jogo_1 = criar_campo (4, 4, 6, 3)
botoes = criar_campo(4, 4, 0, 0)
referencia = criar_campo(4, 4, 0, 0)

#------------------------------------------------------PLACAR JOGADORES------------------------------------------------------

jogador1 = [0, True]
jogador2 = [0, False]
fim = 0
trava = 0

#--------------------------------------------------------Importando o Pygame-------------------------------------------

pygame.init()

#Criando Tela
pygame.display.set_caption('Caça ao Tesouro')
screen = pygame.display.set_mode((1080, 720))
jogo = True

#Cores
white =(255,255,255)
black = (0,0,0)
red = (255,0,0)
green = (100,200,20)
blue = (0,0,255)
brown = (140,110,73)
screen.fill(white)
orange = (140,23,23)

#----------------------------------------------------------MENU INICIAL________________________________________________
regra = pygame.image.load('imagem/regras.jpg')
inicial = pygame.image.load('imagem/inicial.jpg')


screen.blit(inicial,(0,0))
biniciar = pygame.image.load('imagem/iniciar.png')
botao_iniciar = screen.blit(biniciar,(437,495))

pygame.display.update()
inicio = True
while (inicio == True):   
    pos = pygame.mouse.get_pos()
    
    pressed1, pressed2, pressed3 = pygame.mouse.get_pressed()

    if   botao_iniciar.collidepoint(pos) and pressed1 :
        inicio = False        

    pygame.display.update()
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            inicio = False

screen.blit(inicial,(0,0))
botao_iniciar = pygame.draw.rect(screen, brown,(437,495,200,110),2)

pygame.display.update()
inicio2 = True
while (inicio2 == True):   
    pos = pygame.mouse.get_pos()
    
    pressed1, pressed2, pressed3 = pygame.mouse.get_pressed()

    if   botao_iniciar.collidepoint(pos) and pressed1 :
        inicio2 = False
        

    pygame.display.update()
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            inicio2 = False

#---------------------------------------------------------Campo de Jogo-------------------------------------------
praia = pygame.image.load('imagem/janela_principal.jpg')
screen.blit(praia,(0,0))
for i in range(4):
    for j in range(4):
        pygame.draw.rect(screen,brown,(((i+1)*100)+27,((j+1)*100)+121,100,100),4)

#-----------------------------------------------------musica de fundo-------------------------------------------
som_on = pygame.image.load('imagem/somon.png')
som  = screen.blit(som_on,(980,610))
musica= True
pygame.mixer.music.load('hs.mp3')
pygame.mixer.music.play()
pygame.event.wait()
       
#---------------------------------------------------importando imagens-------------------------------------------

buraco = pygame.image.load('imagem/buraco.jpg')
tesouro = pygame.image.load('imagem/bau.jpg')
areia = pygame.image.load('imagem/casa.jpg')
numero0 = pygame.image.load('imagem/0.jpg')
numero1 = pygame.image.load('imagem/1.jpg')
numero2 = pygame.image.load('imagem/2.jpg')
numero3 = pygame.image.load('imagem/3.jpg')
numero4 = pygame.image.load('imagem/4.jpg')
claro = pygame.image.load('imagem/jogador_claro.png')
escuro = pygame.image.load('imagem/jogador_escuro.png')
sair = pygame.image.load('imagem/fechar.png')
empate = pygame.image.load('imagem/empate.jpg')
ganhou_1 = pygame.image.load('imagem/jogador1_ganhou.jpg')
ganhou_2 = pygame.image.load('imagem/jogador2_ganhou.jpg')
som_on = pygame.image.load('imagem/somon.png')
som_off = pygame.image.load('imagem/somoff.png')

#-------------------------------------------------CRIANDO BOTOES-----------------------------------------------

for i in range (len(botoes)):
    for j in range (len(botoes[0])):
        colocando_botoes(screen, botoes, i, j, areia)

pygame.display.update()

#----------------------------------COISAS ESCRITAS NA TELA (FIXAS)--------------------------------------

#-----------------------------------------Fontes------------------------------------------------
pirata = pygame.font.SysFont( "Pirata One" , 40)
pirata1 = pygame.font.SysFont( "Pirata One" , 50)
casa = '0'           
        
while jogo:    
    pos = pygame.mouse.get_pos()

    pressed1, pressed2, pressed3 = pygame.mouse.get_pressed()
          
    if   botoes[0][0].collidepoint(pos) and pressed1 and (referencia[0][0] == 0):
        casa = verificando_casa (campo_jogo_1, 0, 0)
        pontuacao()

    elif som.collidepoint(pos) and pressed1:
        if musica == True:
            pygame.mixer.music.stop()
            pygame.event.wait()
            screen.blit(som_off,(980,610))
            musica = False
        else:
            pygame.mixer.music.play()
            pygame.event.wait()
            screen.blit(som_on,(980,610))
            musica = True

    elif   botoes[1][0].collidepoint(pos) and pressed1 and (referencia[0][1] == 0):
        casa = verificando_casa (campo_jogo_1, 0, 1)
        pontuacao()
    elif   botoes[2][0].collidepoint(pos) and pressed1 and (referencia[0][2] == 0):
        casa = verificando_casa (campo_jogo_1, 0, 2)
        pontuacao()
    elif   botoes[3][0].collidepoint(pos) and pressed1 and (referencia[0][3] == 0):
        casa = verificando_casa (campo_jogo_1, 0, 3)
        pontuacao()
    elif   botoes[0][1].collidepoint(pos) and pressed1 and (referencia[1][0] == 0):
        casa = verificando_casa (campo_jogo_1, 1, 0)        
        pontuacao()
    elif   botoes[1][1].collidepoint(pos) and pressed1 and (referencia[1][1] == 0):
        casa = verificando_casa (campo_jogo_1, 1, 1)
        pontuacao()
    elif   botoes[2][1].collidepoint(pos) and pressed1 and (referencia[1][2] == 0):
        casa = verificando_casa (campo_jogo_1, 1, 2)
        pontuacao()
    elif   botoes[3][1].collidepoint(pos) and pressed1 and (referencia[1][3] == 0):
        casa = verificando_casa (campo_jogo_1, 1, 3)
        pontuacao()
    elif   botoes[0][2].collidepoint(pos) and pressed1 and (referencia[2][0] == 0):
        casa = verificando_casa (campo_jogo_1, 2, 0)
        pontuacao()
    elif   botoes[1][2].collidepoint(pos) and pressed1 and (referencia[2][1] == 0):
        casa = verificando_casa (campo_jogo_1, 2, 1)
        pontuacao()
    elif   botoes[2][2].collidepoint(pos) and pressed1 and (referencia[2][2] == 0):
        casa = verificando_casa (campo_jogo_1, 2, 2)
        pontuacao()
    elif   botoes[3][2].collidepoint(pos) and pressed1 and (referencia[2][3] == 0):
        casa = verificando_casa (campo_jogo_1, 2, 3)
        pontuacao()
    elif   botoes[0][3].collidepoint(pos) and pressed1 and (referencia[3][0] == 0):
        casa = verificando_casa (campo_jogo_1, 3, 0)
        pontuacao()
    elif   botoes[1][3].collidepoint(pos) and pressed1 and (referencia[3][1] == 0):
        casa = verificando_casa (campo_jogo_1, 3, 1)
        pontuacao()
    elif   botoes[2][3].collidepoint(pos) and pressed1 and (referencia[3][2] == 0):
        casa = verificando_casa (campo_jogo_1, 3, 2)
        pontuacao()
    elif   botoes[3][3].collidepoint(pos) and pressed1 and (referencia[3][3] == 0):
        casa = verificando_casa (campo_jogo_1, 3, 3)    
        pontuacao()

    acabou = terminou()
    pygame.display.update()
    if (acabou == 16):
        if (jogador1[0]> jogador2[0]):
            screen.blit(ganhou_1,(0,0))


           #Jogador 1
            
            screen.blit(claro, (188,530))
            texto = pirata1.render("%d" %jogador1[0],False,black)
            screen.blit(texto,(228, 590))
            texto = pirata.render( "Jogador 1",False,black)
            screen.blit(texto,(228,540))

            #Jogador 2
            
            screen.blit(claro, (679,530))
            texto = pirata1.render("%d" %jogador2[0],False,black)
            screen.blit(texto,(720, 590))
            texto = pirata.render( "Jogador 2",False,black)
            screen.blit(texto,(720,540))


            fim = screen.blit(sair, (493,530))
            if   (fim.collidepoint(pos) and pressed1):
                jogo = False
            
        elif(jogador2[0]>jogador1[0]):
            screen.blit(ganhou_2,(0,0))

            #Jogador 1
            
            screen.blit(claro, (188,530))
            texto = pirata1.render("%d" %jogador1[0],False,black)
            screen.blit(texto,(228, 590))
            texto = pirata.render( "Jogador 1",False,black)
            screen.blit(texto,(228,540))

            #Jogador 2
            
            screen.blit(claro, (679,530))
            texto = pirata1.render("%d" %jogador2[0],False,black)
            screen.blit(texto,(720, 590))
            texto = pirata.render( "Jogador 2",False,black)
            screen.blit(texto,(720,540))
            
            fim = screen.blit(sair, (493,530))
            if   (fim.collidepoint(pos) and pressed1):
                jogo = False
    
        elif(jogador1[0]==jogador2[0]):
            screen.blit(empate,(0,0))
            
            #Jogador 1
            
            screen.blit(claro, (188,530))
            texto = pirata1.render("%d" %jogador1[0],False,black)
            screen.blit(texto,(228, 590))
            texto = pirata.render( "Jogador 1",False,black)
            screen.blit(texto,(228,540))

            #Jogador 2
            
            screen.blit(claro, (679,530))
            texto = pirata1.render("%d" %jogador2[0],False,black)
            screen.blit(texto,(720, 590))
            texto = pirata.render( "Jogador 2",False,black)
            screen.blit(texto,(720,540))

            fim = screen.blit(sair, (493,530))
            if   (fim.collidepoint(pos) and pressed1):
                jogo = False
    else:
        placar()

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            jogo = False
pygame.quit()


