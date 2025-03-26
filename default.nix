{ pkgs ? import <nixpkgs> {} }:

let
  inherit (pkgs)
    lib
    callPackage
    fetchFromGitHub
    maven
    jdk8
    aspectj;

  lumosTracingFramework = callPackage (fetchFromGitHub {
    owner = "leochanj105";
    repo = "tracing-framework";
    rev = "50c5f262982aa75823f15315161b10e67b74b4d9";
    hash = "sha256-8ZguP3ntzEaomNqOzbNI3CZ0eEsfr2U5m9YEbw5i/3U=";
  }) { };
in
maven.buildMavenPackage {
  pname = "lumos-tracer";
  version = "0.0.1";

  src = ./.;

  mvnHash = "sha256-ab12AwNIXEEr0p0TOtZi3VCek8Fa5duyCDzWN90lDEg=";

  mvnParameters = lib.escapeShellArgs [
    "clean"
    "install"
    "package"
  ];

  mvnFetchExtraArgs = {
    # Includes packages from the tracing framework.
    preBuild = ''
      mkdir -p $out
      cp -dpR ${lumosTracingFramework.fetchedMavenDeps}/.m2 $out
      chmod +w -R $out/.m2
    '';
  };

  nativeBuildInputs = [
    jdk8
    aspectj
  ];

  mvnJdk = jdk8;

  postInstall = ''
    mv target $out
  '';
}
