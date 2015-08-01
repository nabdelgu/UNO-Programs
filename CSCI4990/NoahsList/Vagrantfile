# -*- mode: ruby -*-
# vi: set ft=ruby :

# Don't provision using this file; it's only for packaging with the image.

Vagrant.configure('2') do |config|
  config.vm.box = "dwa012/4990_SU15"
  config.vm.box_url = "http://www.cs.uno.edu/~daniel/boxes/4990_su15.json"
  config.vm.network :forwarded_port, guest: 3000, host: 3000
  config.vm.network :forwarded_port, guest: 5432, host: 5432
end
