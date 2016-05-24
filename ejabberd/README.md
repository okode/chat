eJabberd
========

Apply valid SSL certificate
---------------------------

    $ cp /opt/ejabberd/ssl/host.pem /opt/ejabberd/ssl/sky.okode.com.pem

Enable `mod_muc_admin`
----------------------

* Enter https://cloud.okode.com:5280/admin
* Go to sky.okode.com > Nodes > ejabberd@ejabberd-1 > Modules
* Add new module `mod_muc_admin` and press `Start`
