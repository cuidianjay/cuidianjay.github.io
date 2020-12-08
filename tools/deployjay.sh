jekyll build

git checkout gh-pages

git pull

rm -rf *

cp -r ../jekyll-temp/* .

git commit -m "update site to No.1"

git push origin gh-pages