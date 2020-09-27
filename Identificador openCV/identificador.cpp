#include "opencv2/core.hpp"
#include "opencv2/imgcodecs.hpp"
#include "opencv2/highgui.hpp"
#include <iostream>

using namespace cv;
using namespace std;

int main(){
    Mat img = imread("exemplo6.png");
    //imshow("Imagem",img);
    //Rect r (55,34,88,154);
    //Mat rect = img(r);
    //imshow("RECT", rect);

    Vec3b P1 = img.at<Vec3b>(77, 134);
    Vec3b P2 = img.at<Vec3b>(154,134);
    Vec3b P3 = img.at<Vec3b>(179, 100);
    Vec3b P4 = img.at<Vec3b>(145, 64);
    Vec3b P5 = img.at<Vec3b>(77, 64);
    Vec3b P6 = img.at<Vec3b>(41, 100);
    Vec3b P7 = img.at<Vec3b>(111, 100);
    Vec3b control = img.at<Vec3b>(77, 100);

    /*cout << "P1 = " << P1 << endl;
    cout << "P2 = " << P2 << endl;
    cout << "P3 = " << P3 << endl;
    cout << "P4 = " << P4 << endl;
    cout << "P5 = " << P5 << endl;
    cout << "P6 = " << P6 << endl;
    cout << "P7 = " << P7 << endl;
    cout << "Control = " << control << endl;
    */
    if (P1[0] && P2[0] && P3[0] && P4[0] && P5[0] && P6[0] && !P7[0]){
        cout << "O numero é 0!" << endl;
    }
    else if (P1[0] && P2[0] && !P3[0] && !P4[0] && !P5[0] && !P6[0] && !P7[0]){
        cout << "O numero é 1!" << endl;
    }
    else if (P1[0] && !P2[0] && P3[0] && P4[0] && !P5[0] && P6[0] && P7[0]){
        cout << "O numero é 2!" << endl;
    }
    else if (P1[0] && P2[0] && P3[0] && !P4[0] && !P5[0] && P6[0] && P7[0]){
        cout << "O numero é 3!" << endl;
    }
    else if (P1[0] && P2[0] && !P3[0] && !P4[0] && P5[0] && !P6[0] && P7[0]){
        cout << "O numero é 4!" << endl;
    }
    else if (!P1[0] && P2[0] && P3[0] && !P4[0] && P5[0] && P6[0] && P7[0]){
        cout << "O numero é 5!" << endl;
    }
    else if (!P1[0] && P2[0] && P3[0] && P4[0] && P5[0] && !P6[0] && P7[0]){
        cout << "O numero é 6!" << endl;
    }
    else if (P1[0] && P2[0] && !P3[0] && !P4[0] && !P5[0] && P6[0] && !P7[0]){
        cout << "O numero é 7!" << endl;
    }
    else if (P1[0] && P2[0] && P3[0] && P4[0] && P5[0] && P6[0] && P7[0]){
        cout << "O numero é 8!" << endl;
    }
    else if (P1[0] && P2[0] && !P3[0] && !P4[0] && P5[0] && P6[0] && P7[0]){
        cout << "O numero é 9!" << endl;
    }
    waitKey(0);
}