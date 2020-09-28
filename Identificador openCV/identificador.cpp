#include "opencv2/core.hpp"
#include "opencv2/imgcodecs.hpp"
#include "opencv2/highgui.hpp"
#include <iostream>
#include <vector>

using namespace cv;
using namespace std;

int retornaValor(vector<int>);
vector <vector <Point>> Pontos(Mat);


int main(){
    Mat img = imread("exemplo2x.png", IMREAD_GRAYSCALE);
    if(img.empty()){
        cout << "A imagem não foi importada" << endl;
        return -1;
    }
    imshow("Imagem",img);
    
    Rect r (55,34,88,154);
    Mat rect = img(r);
    //imshow("RECT", rect);
    vector <vector <Point>> segmentos;

    segmentos = Pontos(img);

    vector <int> p1, p2, p3, p4;

    for (int i = 0; i < segmentos[0].size(); i++){
        p1.push_back((int)img.at<uchar>(segmentos[0][i]));
    }
    
    int var, gas = 10 * retornaValor(p1);

    for (int i = 0; i < segmentos[1].size(); i++){
        p2.push_back((int)img.at<uchar>(segmentos[1][i]));
    }
    
    gas += retornaValor(p2);

    cout << "A porcentagem de Gás é :\t " << gas << "%" << endl;

    for (int i = 0; i < segmentos[2].size(); i++){
        p3.push_back((int)img.at<uchar>(segmentos[2][i]));
        //cout << "Segmento " << i << " - " << segmentos[2][i] << " = " << p4[i] << endl;
    }
    var = retornaValor(p3);
    
    for (int i = 0; i < segmentos[3].size(); i++){
        p4.push_back((int)img.at<uchar>(segmentos[3][i]));
        //cout << "Segmento " << i << " - " << segmentos[3][i] << " = " << p4[i] << endl;
    }
    
    int aux = retornaValor(p4);
    if (aux == -10)       var = (var*-1) - 10;
    else if (aux == 10)   var += 10; 
    else if (aux == -1)   var = (var*-1);
    else if (aux == 1)    var = (var*1);
    cout << "A porcentagem de variação é :\t" << var << "%" << endl;

    waitKey(0);
}

vector<vector <Point>> Pontos(Mat imagem)
{
    vector <vector <Point>> segmentos;
    vector <Point> segmentoA, segmentoB, segmentoC, segmentoD;
    Point a, b, c, d, e, f, h;
    
    a.x = 134 * (imagem.size().height/415), a.y = 77 * (imagem.size().width/415);
    segmentoA.push_back(a);

    b.x =  134 * (imagem.size().height/415), b.y = 145 * (imagem.size().width/415);
    segmentoA.push_back(b);

    c.x = 100 * (imagem.size().height/415), c.y = 179 * (imagem.size().width/415);
    segmentoA.push_back(c);

    d.x = 64 * (imagem.size().height/415), d.y = 145 * (imagem.size().width/415);
    segmentoA.push_back(d);

    e.x = 64 * (imagem.size().height/415), e.y = 77 * (imagem.size().width/415);
    segmentoA.push_back(e);

    f.x = 100 * (imagem.size().height/415), f.y = 41 * (imagem.size().width/415);
    segmentoA.push_back(f);

    h.x = 100 * (imagem.size().height/415), h.y = 111 * (imagem.size().width/415);
    segmentoA.push_back(h);
    
    segmentos.push_back(segmentoA);

    /******************************************************************************/
    
    a.x = 243 * (imagem.size().height/415), a.y = 77 * (imagem.size().width/415);
    segmentoB.push_back(a);

    b.x =  243 * (imagem.size().height/415), b.y = 145 * (imagem.size().width/415);
    segmentoB.push_back(b);

    c.x = 208 * (imagem.size().height/415), c.y = 179 * (imagem.size().width/415);
    segmentoB.push_back(c);

    d.x = 173 * (imagem.size().height/415), d.y = 145 * (imagem.size().width/415);
    segmentoB.push_back(d);

    e.x = 173 * (imagem.size().height/415), e.y = 77 * (imagem.size().width/415);
    segmentoB.push_back(e);

    f.x = 208 * (imagem.size().height/415), f.y = 41 * (imagem.size().width/415);
    segmentoB.push_back(f);

    h.x = 208 * (imagem.size().height/415), h.y = 111 * (imagem.size().width/415);
    segmentoB.push_back(h);

    segmentos.push_back(segmentoB);
    
    /******************************************************************************/


    a.x = 241 * (imagem.size().height/415), a.y = 261 * (imagem.size().width/415);
    segmentoD.push_back(a);

    b.x = 241 * (imagem.size().height/415), b.y = 329 * (imagem.size().width/415);
    segmentoD.push_back(b);

    c.x = 206 * (imagem.size().height/415), c.y = 364 * (imagem.size().width/415);
    segmentoD.push_back(c);

    d.x = 171 * (imagem.size().height/415), d.y = 329 * (imagem.size().width/415);
    segmentoD.push_back(d);

    e.x = 171 * (imagem.size().height/415), e.y = 261 * (imagem.size().width/415);
    segmentoD.push_back(e);

    f.x = 206 * (imagem.size().height/415), f.y = 227 * (imagem.size().width/415);
    segmentoD.push_back(f);

    h.x = 206 * (imagem.size().height/415), h.y = 297 * (imagem.size().width/415);
    segmentoD.push_back(h);
    
    segmentos.push_back(segmentoD);

    /******************************************************************************/

    a.x = 131 * (imagem.size().height/415), a.y = 261 * (imagem.size().width/415);
    segmentoC.push_back(a);

    b.x = 131 * (imagem.size().height/415), b.y = 329 * (imagem.size().width/415);
    segmentoC.push_back(b);

    c.x = 78 * (imagem.size().height/415), c.y = 297 * (imagem.size().width/415);
    segmentoC.push_back(c);

    
    segmentos.push_back(segmentoC);

    /******************************************************************************/
    return segmentos;
}

int retornaValor(vector<int> p){
    if(p.size() == 7){
        if (p[0] && p[1] && p[2] && p[3] && p[4] && p[5] && !p[6]){
            return 0;
        }
        else if (p[0] && p[1] && !p[2] && !p[3] && !p[4] && !p[5] && !p[6]){
            return 1;
        }
        else if (p[0] && !p[1] && p[2] && p[3] && !p[4] && p[5] && p[6]){
            return 2;
        }
        else if (p[0] && p[1] && p[2] && !p[3] && !p[4] && p[5] && p[6]){
            return 3;
        }
        else if (p[0] && p[1] && !p[2] && !p[3] && p[4] && !p[5] && p[6]){
            return 4;
        }
        else if (!p[0] && p[1] && p[2] && !p[3] && p[4] && p[5] && p[6]){
            return 5;
        }
        else if (!p[0] && p[1] && p[2] && p[3] && p[4] && !p[5] && p[6]){
            return 6;
        }
        else if (p[0] && p[1] && !p[2] && !p[3] && !p[4] && p[5] && !p[6]){
            return 7;
        }
        else if (p[0] && p[1] && p[2] && p[3] && p[4] && p[5] && p[6]){
            return 8;
        }
        else if (p[0] && p[1] && !p[2] && !p[3] && p[4] && p[5] && p[6]){
            return 9;
        }
    }
    else if (p.size() == 3){
        if (p[0] && p[1] && p[2]){
            return -10;
        }
        if (p[0] && p[1] && !p[2]){
            return 10;
        }
        if (!p[0] && !p[1] && p[2]){
            return -1;
        }
        if (!p[0] && !p[1] && !p[2]){
            return 1;
        }
    }
}