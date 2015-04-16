### Basics
Set some branch as default when pushing
`git push -u origin master`


##### Merge a repository into another
This allows you to keep the commits of the repo you're merging in.

```
git subtree add --prefix=YOURPREFIX git://github.com/YOURPATH master
```

```
git push [remote name] master
```

### Undo your mistakes

##### Undo since the last commit
`git checkout -f`

##### For the stupider mistakes: Delete/edit commit history
If you accidentally merge a repository and add 1,000 of other people's commits (again), you can do a rebase to undo your mistakes. If you merge one of your own repositories, you will double all of the commits of the merged repository. If you want to undo that, you can also use this.

Decide how many parent commits you want to go back. N = number of parent commits.
```
git rebase -i HEAD~N
```

Follow the instructions in VIM when deciding whether you're editing or deleting.

Some useful things to know about using VIM:

* If you want to delete multiple lines at a time, you can press `ESC` to go into visual mode in VIM, then select the text with your mouse or the keyboard
* To finally delete, you can press `d` or `x`
* To save your work and get out of VIM, you can type `:x` or `:wq`

Once you've done that, you'll probably be asked whether you want to do `git rebase --continue` (to fix the issue) or `git rebase --skip`. I haven't seen any problems with just doing the skip, soooo... 

When you've finished editing the commit history, your current version won't be like the one on GitHub, so you'll have to force a commit. You can do that by typing:

```
git push -f [remote name] [branch name]
```

##### Merge branches together
```
git checkout dat-branch-you-want-to-have-merged-into
git merge dat-branch-you-want-merged-in
```
