#!/usr/bin/env bash
# get branch name
branchName=""

getBranch () {
	local regex='\*[ ]((\w|-)+)'
	local branches=`git branch`
	if [[ ! $branches =~ $regex ]]; then
		echo "Something goes wrong..."
	fi
	branchName="${BASH_REMATCH[1]}"
}
getBranch

echo $branchName

#check is branch clear
checkBranch () {
	msg=`git status | grep 'nothing to commit, working tree clean'`
	echo $msg

	if [ ${#msg} = 0 ]; then
		echo "Working tree is not clear."
		exit 0
	fi
}
checkBranch

## version
#major | minor | patch 
if [ "$#" -eq "0" ]; then
	mode="patch"
else
	mode=$1
fi
npm version $mode

version=`node -p "require('./package.json').version"`
git add -A
git commit -m "build front $version"

##merge branch to master

git checkout master
git pull
checkBranch
git merge -s recursive -X theirs "$branchName"
checkBranch
git push
git checkout "$branchName"
git merge -s recursive -X theirs master

git push
