#include <algorithm>
#include <cmath>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <ctime>
#include <iostream>
#include <list>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

using namespace std;
typedef long long ll;
//类定义：二维向量
class Vector2d {
public:
  double x_;
  double y_;

public:
  Vector2d(double x, double y) : x_(x), y_(y) {}
  Vector2d() : x_(0), y_(0) {}

  //二维向量叉乘,
  double CrossProduct(const Vector2d vec) { return x_ * vec.y_ - y_ * vec.x_; }
  //二维向量减法
  Vector2d Minus(const Vector2d vec) const {
    return Vector2d(x_ - vec.x_, y_ - vec.y_);
  }
};

//三角形类
class Triangle {
public:
  Vector2d pointA_, pointB_, pointC_;

public:
  Triangle(Vector2d point1, Vector2d point2, Vector2d point3)
      : pointA_(point1), pointB_(point2), pointC_(point3) {}

  Triangle() : pointA_(), pointB_(), pointC_(){};

  // t1 = PA^PB, t2 = PB^PC,  t3 = PC^PA, t1,t2,t3 同号则 P在三角形内部
  bool IsPointInTriangle(const Vector2d pointP) {
    Vector2d PA = pointA_.Minus(pointP);
    Vector2d PB = pointB_.Minus(pointP);
    Vector2d PC = pointC_.Minus(pointP);
    double t1 = PA.CrossProduct(PB);
    double t2 = PB.CrossProduct(PC);
    double t3 = PC.CrossProduct(PA);
    return t1 * t2 >= 0 && t1 * t3 >= 0 && t2 * t3 >= 0;
  }
};

int main() {
  Triangle a;
  while (scanf("%lf%lf%lf%lf%lf%lf", &a.pointA_.x_, &a.pointA_.y_,
               &a.pointB_.x_, &a.pointB_.y_, &a.pointC_.x_,
               &a.pointC_.y_) != EOF) {
    Vector2d p;
    scanf("%lf%lf", &p.x_, &p.y_);
    if (a.IsPointInTriangle(p))
      printf("YES\n");
    else
      printf("NO\n");
  }
  return 0;
}