eJabberd
========

Enable `mod_muc_admin`
----------------------

* Enter https://cloud.okode.com:5280/admin
* Go to cloud.okode.com > Nodes > ejabberd@ejabberd-1 > Modules
* Add new module `mod_muc_admin` and press `Start`

Admin utils
-----------

Print status:

    $ ejabberdctl status

Print full JIDs of all established sessions, one on a line:

    $ ejabberdctl connected-users

List  all  the  users  registered  on the ejabberd server:

    $ ejabberdctl  registered-users cloud.okode.com